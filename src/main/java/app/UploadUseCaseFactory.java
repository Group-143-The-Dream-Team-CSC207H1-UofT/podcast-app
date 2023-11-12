package app;

import data_access.EpisodeDataAccess;
import data_access.TranscriptDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.transcribe.TranscribeController;
import interface_adapter.transcribe.TranscribePresenter;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.upload.*;
import use_case.transcribe.TranscribeInputBoundary;
import use_case.transcribe.TranscribeInteractor;
import use_case.transcribe.TranscribeOutputBoundary;
import use_case.upload.*;
import view.UploadView;


public class UploadUseCaseFactory {
        
    public static UploadView create(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, TranscribeViewModel transcribeViewModel, EpisodeDataAccess episodeDataAccess, TranscriptDataAccess transcriptDataAccess) {
        UploadController uploadController = createUploadUseCase(viewManagerModel, uploadViewModel, episodeDataAccess);
        TranscribeController transcribeController = createTranscribeUseCase(viewManagerModel, transcribeViewModel, episodeDataAccess, transcriptDataAccess);
        return new UploadView(uploadController, uploadViewModel, transcribeViewModel, transcribeController);
    }

    private static UploadController createUploadUseCase(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, EpisodeDataAccess episodeDataAccess){
        UploadOutputBoundary uploadOutputBoundary = new UploadPresenter(uploadViewModel, viewManagerModel);
        UploadInputBoundary uploadInteractor = new UploadInteractor(uploadOutputBoundary, episodeDataAccess);
        return new UploadController(uploadInteractor);
    }
        
    private static TranscribeController createTranscribeUseCase(ViewManagerModel viewManagerModel, TranscribeViewModel transcribeViewModel, EpisodeDataAccess episodeDAO, TranscriptDataAccess transcriptDAO) {
        TranscribeOutputBoundary transcribeOutputBoundary = new TranscribePresenter(transcribeViewModel, viewManagerModel);
        TranscribeInputBoundary transcribeInteractor = new TranscribeInteractor(transcribeOutputBoundary, episodeDAO, transcriptDAO);
        return new TranscribeController(transcribeInteractor);
    }
}
