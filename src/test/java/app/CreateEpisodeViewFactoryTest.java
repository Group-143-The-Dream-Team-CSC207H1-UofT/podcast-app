package app;

import api.EmbeddingsInterface;
import api.OpenAIEmbeddings;
import api.TranscriptionInterface;
import api.WhisperTranscription;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.search_index.SearchIndexViewModel;
import interface_adapter.transcribe.TranscribeViewModel;
import org.junit.jupiter.api.Test;
import view.CreateEpisodeView;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEpisodeViewFactoryTest {
    @Test
    public void TestCreateEpisodeViewFactory() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateEpisodeViewModel createEpisodeViewModel = new CreateEpisodeViewModel();
        TranscribeViewModel transcribeViewModel = new TranscribeViewModel();
        SearchIndexViewModel searchIndexViewModel = new SearchIndexViewModel();
        TranscriptDataAccess transcriptDataAccess = new TranscriptDataAccessObject();
        TranscriptionInterface transcriptionInterface = new WhisperTranscription(System.getenv("OPENAI_API_KEY"));
        EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(transcriptDataAccess);
        VectorDatabase vectorDatabase = new PineconeVectorDatabase(System.getenv("PINECONE_API_KEY"), System.getenv("PINECONE_BASE_URL"));
        EmbeddingsInterface embeddingsInterface = new OpenAIEmbeddings(System.getenv("OPENAI_API_KEY"));
        PodcastDataAccess podcastDataAccess = new PodcastDataAccessObject(episodeDataAccess);

        CreateEpisodeView createEpisodeView = CreateEpisodeViewFactory.create(viewManagerModel, createEpisodeViewModel, transcribeViewModel, searchIndexViewModel, episodeDataAccess, transcriptDataAccess, transcriptionInterface, vectorDatabase, embeddingsInterface, podcastDataAccess);
        assertNotNull(createEpisodeView);
    }
}
