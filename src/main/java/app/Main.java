package app;

import javax.swing.*;

import api.*;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_podcast.CreatePodcastViewModel;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_index.SearchIndexViewModel;
import interface_adapter.summary.SummaryController;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.create_episode.*;
import view.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String, String> loadEnv(String filename) throws IOException {
        Map<String, String> envMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    // Ignore empty lines and comments
                    continue;
                }
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid line: " + line);
                }
                envMap.put(parts[0].trim(), parts[1].trim());
            }
        }
        return envMap;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> env = loadEnv(".env");
            String OPENAI_API_KEY = env.get("OPENAI_API_KEY");
            String PINECONE_API_KEY = env.get("PINECONE_API_KEY");
            String PINECONE_BASE_URL = env.get("PINECONE_BASE_URL");

            JFrame application = new JFrame("Show Notes");
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CardLayout cardLayout = new CardLayout();

            // The various View objects. Only one view is visible at a time.
            JPanel views = new JPanel(cardLayout);
            application.add(views);

            // This keeps track of and manages which view is currently showing.
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            new ViewManager(views, cardLayout, viewManagerModel);

            // api objects
            WhisperTranscription transcriptionObject = new WhisperTranscription(OPENAI_API_KEY);
            EmbeddingsInterface embeddings = new OpenAIEmbeddings(OPENAI_API_KEY);
            VectorDatabase vectorDatabase = new PineconeVectorDatabase(PINECONE_API_KEY, PINECONE_BASE_URL);
            SummaryAPIInterface summaryAPI = new ChatGPTSummary(OPENAI_API_KEY);

            // DAOs
            TranscriptDataAccess transcriptDataAccessObject = new TranscriptDataAccessObject();
            EpisodeDataAccess episodeDataAccessObject = new EpisodeDataAccessObject(transcriptDataAccessObject);
            PodcastDataAccess podcastDataAccessObject = new PodcastDataAccessObject(episodeDataAccessObject);

            // view models
            CreateEpisodeViewModel createEpisodeViewModel = new CreateEpisodeViewModel();
            TranscribeViewModel transcribeViewModel = new TranscribeViewModel();
            SearchIndexViewModel searchIndexViewModel = new SearchIndexViewModel();
            SearchViewModel searchViewModel = new SearchViewModel();
            EpisodeViewModel episodeViewModel = new EpisodeViewModel();
            HomeViewModel homeViewModel = new HomeViewModel();
            PodcastViewModel podcastViewModel = new PodcastViewModel();
            CreatePodcastViewModel createPodcastViewModel = new CreatePodcastViewModel();

            CreateEpisodeView createEpisodeView = CreateEpisodeViewFactory.create(viewManagerModel, podcastViewModel, createEpisodeViewModel, transcribeViewModel, searchIndexViewModel, episodeDataAccessObject, transcriptDataAccessObject, transcriptionObject, vectorDatabase, embeddings, podcastDataAccessObject);
            views.add(createEpisodeView, createEpisodeView.viewName);

            SearchView searchView = SearchViewFactory.create(viewManagerModel, searchViewModel, episodeViewModel, podcastViewModel, podcastDataAccessObject, episodeDataAccessObject, vectorDatabase, embeddings);
            views.add(searchView.panel, searchView.viewName);

            SummaryController summaryController = SummaryUseCaseFactory.create(episodeViewModel, summaryAPI, episodeDataAccessObject);
            EpisodeView episodeView = new EpisodeView(viewManagerModel, episodeViewModel, summaryController);
            views.add(episodeView.panel, episodeView.viewName);

            HomeView homeView = HomeViewFactory.create(viewManagerModel, homeViewModel, podcastViewModel, createEpisodeViewModel, podcastDataAccessObject);
            views.add(homeView.panel, homeView.viewName);

            PodcastView podcastView = PodcastViewFactory.create(viewManagerModel, podcastViewModel, episodeViewModel, createEpisodeViewModel, homeViewModel, podcastDataAccessObject, episodeDataAccessObject);
            views.add(podcastView.panel, podcastView.viewName);

            CreatePodcastView createPodcastView = CreatePodcastViewFactory.create(viewManagerModel, createPodcastViewModel, podcastViewModel, podcastDataAccessObject);
            views.add(createPodcastView, createPodcastView.viewName);

            // set home page
            viewManagerModel.setActiveView(homeView.viewName);
            viewManagerModel.firePropertyChanged();

            application.setSize(960, 540);
            application.setLocationRelativeTo(null);
            application.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
