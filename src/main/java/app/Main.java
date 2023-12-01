package app;

import javax.swing.*;
import api.EmbeddingsInterface;
import api.OpenAIEmbeddings;
import api.WhisperTranscription;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_episode.DisplayEpisodeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_index.SearchIndexViewModel;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.upload.*;
import view.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
        String PINECONE_API_KEY = System.getenv("PINECONE_API_KEY");
        String PINECONE_BASE_URL = System.getenv("PINECONE_BASE_URL");

        JFrame application = new JFrame("Show Notes");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        WhisperTranscription transcriptionObject = new WhisperTranscription(OPENAI_API_KEY);
        EmbeddingsInterface embeddings = new OpenAIEmbeddings(OPENAI_API_KEY);
        VectorDatabase vectorDatabase = new PineconeVectorDatabase(PINECONE_API_KEY, PINECONE_BASE_URL);

        TranscriptDataAccess transcriptDataAccessObject = new TranscriptDataAccessObject();
        EpisodeDataAccess episodeDataAccessObject = new EpisodeDataAccessObject(transcriptDataAccessObject);
        UploadViewModel uploadViewModel = new UploadViewModel();
        TranscribeViewModel transcribeViewModel = new TranscribeViewModel();
        SearchIndexViewModel searchIndexViewModel = new SearchIndexViewModel();
        UploadView uploadView = UploadViewFactory.create(viewManagerModel, uploadViewModel, transcribeViewModel, searchIndexViewModel, episodeDataAccessObject, transcriptDataAccessObject, transcriptionObject, vectorDatabase, embeddings);
        views.add(uploadView, uploadView.viewName);

        SearchViewModel searchViewModel = new SearchViewModel();
        SearchView searchView = SearchViewFactory.create(viewManagerModel, searchViewModel, episodeDataAccessObject, vectorDatabase, embeddings);
        views.add(searchView.panel, searchView.viewName);

        DisplayEpisodeViewModel displayEpisodeViewModel = new DisplayEpisodeViewModel();
        // TODO: we have not implemented and use cases for the episode view yet so it is manually created here, but once implemented, we need a factory.
        EpisodeView episodeView = new EpisodeView(displayEpisodeViewModel);
        views.add(episodeView, episodeView.viewName);

        PodcastViewModel podcastViewModel = new PodcastViewModel();
        PodcastView podcastView = PodcastViewFactory.create(viewManagerModel, podcastViewModel, displayEpisodeViewModel, episodeDataAccessObject);
        views.add(podcastView, podcastView.viewName);

        PodcastDataAccess podcastDAO = new PodcastDataAccessObject(episodeDataAccessObject, null);
        DisplayPodcastViewModel displayPodcastViewModel = new DisplayPodcastViewModel();
        UploadPodcastView uploadPodcastView = UploadPodcastFactory.create(viewManagerModel, displayPodcastViewModel, podcastDAO);
        views.add(uploadPodcastView, uploadPodcastView.viewName);

        viewManagerModel.setActiveView(uploadPodcastView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setSize(960, 540);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
