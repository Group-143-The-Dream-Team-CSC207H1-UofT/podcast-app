package interface_adapter.create_episode;

import interface_adapter.ViewManagerModel;
import use_case.create_episode.CreateEpisodeOutputBoundary;
import use_case.create_episode.CreateEpisodeOutputData;

public class CreateEpisodePresenter implements CreateEpisodeOutputBoundary {
  
    private final CreateEpisodeViewModel viewModel;
    private final ViewManagerModel viewManagerModel;  // TODO: remove if not needed

    public CreateEpisodePresenter(CreateEpisodeViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateEpisodeOutputData outputData) {
        CreateEpisodeState currentState = viewModel.getState();
        currentState.setEpisode(outputData.getEpisode());
        currentState.setErrorMessage("");
        viewModel.setState(currentState);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateEpisodeState currentState = viewModel.getState();
        currentState.setEpisode(null);
        currentState.setErrorMessage(error);
        viewModel.setState(currentState);
        viewModel.firePropertyChanged();
    }
}
