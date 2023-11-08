package app;

import data_access.EpisodeDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.upload.*;
import use_case.upload.*;
import view.UploadView;


public class UploadFactory {

    public static UploadView create(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, EpisodeDataAccess episodeDataAccess) {
        UploadController uploadController = createUploadUseCase(viewManagerModel, uploadViewModel, episodeDataAccess);
        return new UploadView(uploadController, uploadViewModel);
    }

    private static UploadController createUploadUseCase(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, EpisodeDataAccess episodeDataAccess){
        UploadOutputBoundary uploadOutputBoundary = new UploadPresenter(uploadViewModel, viewManagerModel);
        UploadInputBoundary uploadInteractor = new UploadInteractor(uploadOutputBoundary, episodeDataAccess);
        return new UploadController(uploadInteractor);
    }
}
