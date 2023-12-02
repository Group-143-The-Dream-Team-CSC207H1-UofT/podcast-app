package app;

import api.EmbeddingsInterface;
import api.TranscriptionInterface;
import data_access.EpisodeDataAccess;
import data_access.PodcastDataAccess;
import data_access.TranscriptDataAccess;
import data_access.VectorDatabase;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_index.SearchIndexController;
import interface_adapter.search_index.SearchIndexPresenter;
import interface_adapter.search_index.SearchIndexViewModel;
import interface_adapter.transcribe.TranscribeController;
import interface_adapter.transcribe.TranscribePresenter;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.create_episode.*;
import use_case.search_index.SearchIndexInputBoundary;
import use_case.search_index.SearchIndexInteractor;
import use_case.search_index.SearchIndexOutputBoundary;
import use_case.transcribe.TranscribeInputBoundary;
import use_case.transcribe.TranscribeInteractor;
import use_case.transcribe.TranscribeOutputBoundary;
import use_case.create_episode.*;
import view.CreateEpisodeView;


public class CreateEpisodeViewFactory {
    /**
     * Factory method to create and configure a CreateEpisodeView instance.
     * This method initializes controllers for creating an episode, transcribing, and
     * indexing search data. It then uses these controllers to create a CreateEpisodeView.
     *
     * @param viewManagerModel The model managing different views.
     * @param createEpisodeViewModel ViewModel for creating an episode.
     * @param transcribeViewModel ViewModel for the transcription process.
     * @param searchIndexViewModel ViewModel for managing search indexing.
     * @param episodeDataAccess Data access object for episode-related operations.
     * @param transcriptDataAccess Data access object for transcript-related operations.
     * @param transcriptionObject Object for transcription functionality.
     * @param vectorDatabase Database for storing vector representations.
     * @param embeddings Interface for generating embeddings.
     * @param podcastDAO Data access object for podcast-related operations.
     * @return An instance of CreateEpisodeView fully configured with the necessary controllers.
     */
    public static CreateEpisodeView create(ViewManagerModel viewManagerModel, CreateEpisodeViewModel createEpisodeViewModel, TranscribeViewModel transcribeViewModel, SearchIndexViewModel searchIndexViewModel, EpisodeDataAccess episodeDataAccess, TranscriptDataAccess transcriptDataAccess, TranscriptionInterface transcriptionObject, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings, PodcastDataAccess podcastDAO) {
        CreateEpisodeController createEpisodeController = createEpisodeUseCase(viewManagerModel, createEpisodeViewModel, episodeDataAccess, podcastDAO);
        TranscribeController transcribeController = createTranscribeUseCase(viewManagerModel, transcribeViewModel, episodeDataAccess, transcriptDataAccess, transcriptionObject);
        SearchIndexController searchIndexController = createSearchIndexUseCase(viewManagerModel, searchIndexViewModel, vectorDatabase, embeddings);
        return new CreateEpisodeView(viewManagerModel, createEpisodeController, createEpisodeViewModel, transcribeViewModel, transcribeController, searchIndexViewModel, searchIndexController);
    }

    private static CreateEpisodeController createEpisodeUseCase(ViewManagerModel viewManagerModel, CreateEpisodeViewModel createEpisodeViewModel, EpisodeDataAccess episodeDataAccess, PodcastDataAccess podcastDAO){
        CreateEpisodeOutputBoundary createEpisodeOutputBoundary = new CreateEpisodePresenter(createEpisodeViewModel, viewManagerModel);
        CreateEpisodeInputBoundary uploadInteractor = new CreateEpisodeInteractor(createEpisodeOutputBoundary, episodeDataAccess, podcastDAO);
        return new CreateEpisodeController(uploadInteractor);
    }
        
    private static TranscribeController createTranscribeUseCase(ViewManagerModel viewManagerModel, TranscribeViewModel transcribeViewModel, EpisodeDataAccess episodeDAO, TranscriptDataAccess transcriptDAO, TranscriptionInterface transcriptionObject) {
        TranscribeOutputBoundary transcribeOutputBoundary = new TranscribePresenter(transcribeViewModel, viewManagerModel);
        TranscribeInputBoundary transcribeInteractor = new TranscribeInteractor(transcribeOutputBoundary, episodeDAO, transcriptDAO, transcriptionObject);
        return new TranscribeController(transcribeInteractor);
    }

    private static SearchIndexController createSearchIndexUseCase(ViewManagerModel viewManagerModel, SearchIndexViewModel viewModel, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings) {
        SearchIndexOutputBoundary outputBoundary = new SearchIndexPresenter(viewModel, viewManagerModel);
        SearchIndexInputBoundary searchIndexInteractor = new SearchIndexInteractor(vectorDatabase, embeddings, outputBoundary);
        return new SearchIndexController(searchIndexInteractor);
    }
}
