package interface_adapter.upload;

import interface_adapter.ViewManagerModel;
import use_case.upload.UploadOutputBoundary;
import use_case.upload.UploadOutputData;

public class UploadPresenter implements UploadOutputBoundary {
    private final UploadViewModel uploadViewModel;
    private final ViewManagerModel viewManagerModel;

    public UploadPresenter(UploadViewModel clearViewModel, ViewManagerModel viewManagerModel) {
        this.uploadViewModel = clearViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(UploadOutputData outputData) {
        UploadState currentState = uploadViewModel.getState();
        currentState.setEpisode(outputData.getEpisode());
        currentState.setErrorMessage("");
        uploadViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UploadState currentState = uploadViewModel.getState();
        currentState.setEpisode(null);
        currentState.setErrorMessage(error);
        uploadViewModel.firePropertyChanged();
    }
}