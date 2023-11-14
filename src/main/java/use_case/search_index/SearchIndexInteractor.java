package use_case.search_index;

import api.EmbeddingsInterface;
import data_access.PineconeVectorDatabase;
import data_access.VectorDatabase;
import entities.Episode;
import entities.TextChunk;
import entities.Transcript;

import java.util.ArrayList;
import java.util.List;

public class SearchIndexInteractor implements SearchIndexInputBoundary {

    private final VectorDatabase vectorDatabase;
    private final EmbeddingsInterface embeddingsGenerator;
    
    private final SearchIndexOutputBoundary outputBoundary;

    public SearchIndexInteractor(VectorDatabase vectorDatabase, EmbeddingsInterface embeddings, SearchIndexOutputBoundary outputBoundary) {
        this.vectorDatabase = vectorDatabase;
        this.embeddingsGenerator = embeddings;
        this.outputBoundary = outputBoundary;
    }


    @Override
    public void execute(SearchIndexInputData inputData) {
        Episode episode = inputData.getEpisode();
        Transcript transcript = episode.getTranscript();

        List<String> values = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        values.add(episode.getTitle());
        ids.add(episode.getId().toString());
        int i = 0;
        for (TextChunk textChunk : transcript.getTextChunks()) {
            values.add(textChunk.getText());
            ids.add(String.format("%s+%s", episode.getId(), i++));
        }

        List<float[]> vectors = embeddingsGenerator.getEmbeddings(values);
        
        if (vectors == null) {
            outputBoundary.prepareFailView("Error occurred during transcription indexing for search.");
        }
        
        if (vectorDatabase.insertVectors(vectors, ids)) {
            outputBoundary.prepareSuccessView(new SearchIndexOutputData(episode));
        } else {
            outputBoundary.prepareFailView("Error occurred when saving indexes for search.");
        }
    }
}
