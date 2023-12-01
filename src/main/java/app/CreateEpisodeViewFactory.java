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
