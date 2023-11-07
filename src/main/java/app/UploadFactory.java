package app;

import data_access.MediaItemDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.upload.*;
import use_case.upload.*;
import view.UploadView;


public class UploadFactory {

    public static UploadView create(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, MediaItemDataAccess mediaItemDataAccess) {
        UploadController uploadController = createUploadUseCase(viewManagerModel, uploadViewModel, mediaItemDataAccess);
        return new UploadView(uploadController, uploadViewModel);
    }

    private static UploadController createUploadUseCase(ViewManagerModel viewManagerModel, UploadViewModel uploadViewModel, MediaItemDataAccess mediaItemDataAccess){
        UploadOutputBoundary uploadOutputBoundary = new UploadPresenter(uploadViewModel, viewManagerModel);
        UploadInputBoundary uploadInteractor = new UploadInteractor(uploadOutputBoundary, mediaItemDataAccess);
        return new UploadController(uploadInteractor);
    }
}
