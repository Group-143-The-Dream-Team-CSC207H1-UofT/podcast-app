package app;

import api.EmbeddingsInterface;
import api.OpenAIEmbeddings;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import interface_adapter.search.SearchViewModel;
import org.junit.jupiter.api.Test;
import view.SearchView;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SearchViewFactoryTest {
    @Test
    public void TestSearchViewFactory() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        EpisodeViewModel episodeViewModel = new EpisodeViewModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        VectorDatabase vectorDatabase = new PineconeVectorDatabase(System.getenv("PINECONE_API_KEY"), System.getenv("PINECONE_BASE_URL"));
        EmbeddingsInterface embeddingsInterface = new OpenAIEmbeddings(System.getenv("OPENAI_API_KEY"));
        PodcastDataAccess podcastDataAccess = new PodcastDataAccessObject(episodeDataAccess);
        SearchView view = SearchViewFactory.create(viewManagerModel, searchViewModel, episodeViewModel, podcastViewModel, podcastDataAccess, episodeDataAccess, vectorDatabase, embeddingsInterface);
        assertNotNull(view);
    }
}
