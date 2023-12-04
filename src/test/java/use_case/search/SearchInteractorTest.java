package use_case.search;

import api.EmbeddingsInterface;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import data_access.VectorDatabase;
import entities.Episode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {
    @Test
    public void TestSearchUseCaseForEpisode() {
        EpisodeDataAccess episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        UUID id = UUID.randomUUID();
        Episode episode = new Episode(id, UUID.randomUUID(),"title", "description", null, null);
        episodeDAO.saveEpisode(episode);
        VectorDatabase vectorDatabase = new VectorDatabase() {
            @Override
            public boolean insertVector(float[] vector, String id) {
                fail("No vectors should be inserted in the search use case.");
                return false;
            }

            @Override
            public boolean insertVectors(List<float[]> vectors, List<String> ids) {
                fail("No vectors should be inserted in the search use case.");
                return false;
            }

            @Override
            public List<String> query(float[] vector) {
                List<String> values = new ArrayList<>();
                values.add(id.toString());
                return values;
            }
        };
        EmbeddingsInterface embeddings = new EmbeddingsInterface() {
            @Override
            public float[] getEmbedding(String value) {
                return new float[1536];
            }

            @Override
            public List<float[]> getEmbeddings(List<String> values) {
                fail("We shouldn't be calling this method in search use case.");
                return null;
            }
        };
        SearchOutputBoundary outputBoundary = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                List<SearchResult> results = new ArrayList<>(outputData.getSearchResults());
                assertEquals(results.size(), 1);
                assert(results.get(0)  instanceof EpisodeSearchResult);
                EpisodeSearchResult searchResult = (EpisodeSearchResult) results.get(0);
                Episode episode1 = searchResult.getEpisode();
                assertEquals(episode.getId(), episode1.getId());
                assertEquals(episode.getTitle(), episode1.getTitle());
                assertEquals(episode.getItemDescription(), episode1.getItemDescription());
            }

            @Override
            public void prepareFailView(String error) {
                fail("There should be no failure here.");
            }
        };

        SearchInteractor interactor = new SearchInteractor(outputBoundary, vectorDatabase, embeddings, episodeDAO);
        interactor.execute("test");
    }

    @AfterAll
    static void clearCSVFiles() {
        try {
            FileWriter episodeCSVWriter = new FileWriter(new File(SearchInteractorTest.class.getResource("/episodes.csv").toURI()));
            episodeCSVWriter.write("id,title,description,transcriptId,summary\n");
            episodeCSVWriter.close();
        } catch (URISyntaxException e) {
            System.out.println("Error in URI syntax.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in file writing.");
            e.printStackTrace();
        }
    }
}