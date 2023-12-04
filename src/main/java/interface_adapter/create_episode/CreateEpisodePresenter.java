package interface_adapter.create_episode;

import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;
import use_case.create_episode.CreateEpisodeOutputBoundary;
import use_case.create_episode.CreateEpisodeOutputData;

public class CreateEpisodePresenter implements CreateEpisodeOutputBoundary {
  
    private final CreateEpisodeViewModel viewModel;
    private final ViewManagerModel viewManagerModel;  // TODO: remove if not needed
    private final PodcastViewModel podcastViewModel;

    public CreateEpisodePresenter(CreateEpisodeViewModel viewModel, PodcastViewModel podcastViewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.podcastViewModel = podcastViewModel;
    }

    /**
     * Updates the EpisodeViewModel to display current episode and updates PodcastViewModel to display new episode.
     * @param outputData the output data from the input boundary.
     */
    @Override
    public void prepareSuccessView(CreateEpisodeOutputData outputData) {
        CreateEpisodeState currentState = viewModel.getState();
        PodcastState podcastState = podcastViewModel.getState();
        currentState.setEpisode(outputData.getEpisode());
        podcastState.setCurrentPodcast(outputData.getPodcast());
        currentState.setErrorMessage("");
        viewModel.setState(currentState);
        podcastViewModel.setState(podcastState);
        viewModel.firePropertyChanged();
        podcastViewModel.firePropertyChanged();
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
