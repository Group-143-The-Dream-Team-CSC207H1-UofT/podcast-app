package interface_adapter.transcribe;

import interface_adapter.ViewManagerModel;
import use_case.transcribe.TranscribeOutputBoundary;
import use_case.transcribe.TranscribeOutputData;

public class TranscribePresenter implements TranscribeOutputBoundary {

    private final TranscribeViewModel transcribeViewModel;

    private final ViewManagerModel viewManagerModel;  // TODO: remove if not needed

    public TranscribePresenter(TranscribeViewModel transcribeViewModel, ViewManagerModel viewManagerModel) {
        this.transcribeViewModel = transcribeViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareSuccessView(TranscribeOutputData outputData) {
        TranscribeState transcribeState = transcribeViewModel.getState();
        transcribeState.setEpisode(outputData.getEpisode());
        transcribeState.setErrorMessage("");
        this.transcribeViewModel.setState(transcribeState);
        transcribeViewModel.firePropertyChanged();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareFailView(String error) {
        TranscribeState transcribeState = transcribeViewModel.getState();
        transcribeState.setEpisode(null);
        transcribeState.setErrorMessage(error);
        this.transcribeViewModel.setState(transcribeState);
        transcribeViewModel.firePropertyChanged();
    }
}
