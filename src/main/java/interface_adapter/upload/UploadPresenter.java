package interface_adapter.upload;

import interface_adapter.ViewManagerModel;
import use_case.create_episode.UploadOutputBoundary;
import use_case.create_episode.UploadOutputData;

public class UploadPresenter implements UploadOutputBoundary {
  
    private final UploadViewModel uploadViewModel;
    private final ViewManagerModel viewManagerModel;  // TODO: remove if not needed

    public UploadPresenter(UploadViewModel clearViewModel, ViewManagerModel viewManagerModel) {
        this.uploadViewModel = clearViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(UploadOutputData outputData) {
        UploadState currentState = uploadViewModel.getState();
        currentState.setEpisode(outputData.getEpisode());
        currentState.setErrorMessage("");
        this.uploadViewModel.setState(currentState);
        uploadViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UploadState currentState = uploadViewModel.getState();
        currentState.setEpisode(null);
        currentState.setErrorMessage(error);
        this.uploadViewModel.setState(currentState);
        uploadViewModel.firePropertyChanged();
    }
}
