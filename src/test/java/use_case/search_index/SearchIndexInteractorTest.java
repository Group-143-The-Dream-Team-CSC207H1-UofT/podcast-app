package use_case.search_index;

import api.EmbeddingsInterface;
import data_access.*;
import entities.Episode;
import entities.TextChunk;
import entities.Transcript;
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

class SearchIndexInteractorTest {
    @Test
    public void TestSearchIndexUseCase() {
        TranscriptDataAccess transcriptDAO = new TranscriptDataAccessObject();
        EpisodeDataAccess episodeDAO = new EpisodeDataAccessObject(transcriptDAO);
        UUID episodeId = UUID.randomUUID();
        List<TextChunk> textChunks = new ArrayList<>();
        textChunks.add(new TextChunk(0, 1000, "test"));
        Transcript transcript = new Transcript(episodeId, "test", textChunks);
        Episode episode = new Episode(episodeId, UUID.randomUUID(),"title", "description", transcript, null);
        transcriptDAO.saveTranscript(transcript);
        episodeDAO.saveEpisode(episode);

        VectorDatabase vectorDatabase = new VectorDatabase() {
            @Override
            public boolean insertVector(float[] vector, String id) {
                fail("No singular vectors should be inserted in the search index use case.");
                return false;
            }

            @Override
            public boolean insertVectors(List<float[]> vectors, List<String> ids) {
                assertEquals(vectors.size(), 2);
                assertEquals(ids.size(), 2);
                assertEquals(ids.get(0), episodeId.toString());
                assertEquals(ids.get(1), String.format("%s+%d", episodeId, 0));
                return true;
            }

            @Override
            public List<String> query(float[] vector) {
                fail("No vectors should be queried in the search index use case.");
                return null;
            }
        };
        EmbeddingsInterface embeddings = new EmbeddingsInterface() {
            @Override
            public float[] getEmbedding(String value) {
                fail("This shouldn't be called in this test.");
                return null;
            }

            @Override
            public List<float[]> getEmbeddings(List<String> values) {
                List<float[]> vectors = new ArrayList<>();
                vectors.add(new float[1536]);
                vectors.add(new float[1536]);
                return vectors;
            }
        };
        SearchIndexOutputBoundary outputBoundary = new SearchIndexOutputBoundary() {

            @Override
            public void prepareSuccessView(SearchIndexOutputData outputData) {
                assertEquals(outputData.getEpisode(), episode);
            }

            @Override
            public void prepareFailView(String error) {
                fail("This shouldn't fail.");
            }
        };

        SearchIndexInteractor interactor = new SearchIndexInteractor(vectorDatabase, embeddings, outputBoundary);
        interactor.execute(new SearchIndexInputData(episode));
    }

    @AfterAll
    static void clearCSVFiles() {
        try {
            FileWriter episodeCSVWriter = new FileWriter(new File(SearchIndexInteractorTest.class.getResource("/episodes.csv").toURI()));
            episodeCSVWriter.write("id,title,description,transcriptId,summary\n");
            episodeCSVWriter.close();
        } catch (URISyntaxException e) {
            System.out.println("Error in URI syntax.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in file writing.");
            e.printStackTrace();
        }
        try {
            FileWriter transciptCSVWriter = new FileWriter(new File(SearchIndexInteractorTest.class.getResource("/transcripts.csv").toURI()));
            transciptCSVWriter.write("id,text\n");
            transciptCSVWriter.close();
        } catch (URISyntaxException e) {
            System.out.println("Error in URI syntax.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in file writing.");
            e.printStackTrace();
        }
    }
}