package use_case.search;

import api.EmbeddingsInterface;
import data_access.EpisodeDataAccess;
import data_access.VectorDatabase;
import entities.Episode;
import entities.TextChunk;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchInteractor implements SearchInputBoundary {
    private final VectorDatabase vectorDatabase;
    private final EmbeddingsInterface embeddingsGenerator;
    private final EpisodeDataAccess episodeDAO;
    private final SearchOutputBoundary outputBoundary;

    /**
     * Construct a SearchInteractor.
     * @param outputBoundary
     * @param vectorDatabase
     * @param embeddings
     * @param episodeDAO
     */
    public SearchInteractor(SearchOutputBoundary outputBoundary, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings, EpisodeDataAccess episodeDAO) {
        this.outputBoundary = outputBoundary;
        this.vectorDatabase = vectorDatabase;
        this.embeddingsGenerator = embeddings;
        this.episodeDAO = episodeDAO;
    }

    /**
     * Query the vector database for values similar to the query and display them to the outputBoundary.
     * @param query
     */
    public void execute(String query) {
        float[] vector = embeddingsGenerator.getEmbedding(query);
        List<String> resultIds = vectorDatabase.query(vector);
        List<SearchResult> searchResults = new ArrayList<>();
        for (String resultId : resultIds) {
            String[] parts = resultId.split("\\+");
            UUID episodeId = UUID.fromString(parts[0]);
            Episode episode = episodeDAO.getEpisodeById(episodeId);
            if (parts.length == 1) {
                searchResults.add(new EpisodeSearchResult(episode));
            } else {
                TextChunk textChunk = episode.getTranscript().getTextChunks().get(Integer.parseInt(parts[1]));
                searchResults.add(new TextChunkSearchResult(episode, textChunk));
            }
        }
        outputBoundary.prepareSuccessView(new SearchOutputData(searchResults));
    }
}
