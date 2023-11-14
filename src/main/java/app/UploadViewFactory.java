package app;

import api.EmbeddingsInterface;
import api.TranscriptionInterface;
import data_access.EpisodeDataAccess;
import data_access.TranscriptDataAccess;
import data_access.VectorDatabase;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_index.SearchIndexController;
import interface_adapter.search_index.SearchIndexPresenter;
import interface_adapter.transcribe.TranscribeController;
import interface_adapter.transcribe.TranscribePresenter;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.upload.*;
import use_case.search_index.SearchIndexInputBoundary;
import use_case.search_index.SearchIndexInteractor;
import use_case.search_index.SearchIndexOutputBoundary;
import use_case.transcribe.TranscribeInputBoundary;
import use_case.transcribe.TranscribeInteractor;
import use_case.transcribe.TranscribeOutputBoundary;
import use_case.upload.*;
import view.UploadView;


public class UploadViewFactory {
        
    public static UploadView create(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, TranscribeViewModel transcribeViewModel, EpisodeDataAccess episodeDataAccess, TranscriptDataAccess transcriptDataAccess, TranscriptionInterface transcriptionObject, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings) {
        UploadController uploadController = createUploadUseCase(viewManagerModel, uploadViewModel, episodeDataAccess);
        TranscribeController transcribeController = createTranscribeUseCase(viewManagerModel, transcribeViewModel, episodeDataAccess, transcriptDataAccess, transcriptionObject);
        SearchIndexController searchIndexController = createSearchIndexUseCase(vectorDatabase, embeddings);
        return new UploadView(uploadController, uploadViewModel, transcribeViewModel, transcribeController, searchIndexController);
    }

    private static UploadController createUploadUseCase(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, EpisodeDataAccess episodeDataAccess){
        UploadOutputBoundary uploadOutputBoundary = new UploadPresenter(uploadViewModel, viewManagerModel);
        UploadInputBoundary uploadInteractor = new UploadInteractor(uploadOutputBoundary, episodeDataAccess);
        return new UploadController(uploadInteractor);
    }
        
    private static TranscribeController createTranscribeUseCase(ViewManagerModel viewManagerModel, TranscribeViewModel transcribeViewModel, EpisodeDataAccess episodeDAO, TranscriptDataAccess transcriptDAO, TranscriptionInterface transcriptionObject) {
        TranscribeOutputBoundary transcribeOutputBoundary = new TranscribePresenter(transcribeViewModel, viewManagerModel);
        TranscribeInputBoundary transcribeInteractor = new TranscribeInteractor(transcribeOutputBoundary, episodeDAO, transcriptDAO, transcriptionObject);
        return new TranscribeController(transcribeInteractor);
    }

    private static SearchIndexController createSearchIndexUseCase(VectorDatabase vectorDatabase, EmbeddingsInterface embeddings) {
        SearchIndexOutputBoundary outputBoundary = new SearchIndexPresenter();
        SearchIndexInputBoundary searchIndexInteractor = new SearchIndexInteractor(vectorDatabase, embeddings, outputBoundary);
        return new SearchIndexController(searchIndexInteractor);
    }
}
