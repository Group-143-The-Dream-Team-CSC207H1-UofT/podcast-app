package interface_adapter.display_podcast;

import interface_adapter.ViewManagerModel;
import use_case.create_podcast.CreatePodcastOutputBoundary;
import use_case.create_podcast.CreatePodcastOutputData;


public class DisplayCreatePodcastPresenter implements CreatePodcastOutputBoundary {

    private final DisplayPodcastViewModel displayPodcastViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayCreatePodcastPresenter(DisplayPodcastViewModel displayPodcastViewModel, ViewManagerModel viewManagerModel) {
        this.displayPodcastViewModel = displayPodcastViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreatePodcastOutputData podcastData) {
        DisplayPodcastState currentState = this.displayPodcastViewModel.getState();
        currentState.setCurrentPodcast(podcastData.getPodcast());
        currentState.setErrorMessage("");
        this.displayPodcastViewModel.setState(currentState);
        this.displayPodcastViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(displayPodcastViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String error) {
        DisplayPodcastState currentState = this.displayPodcastViewModel.getState();
        currentState.setCurrentPodcast(null);
        currentState.setErrorMessage(error);
        this.displayPodcastViewModel.setState(currentState);
        this.displayPodcastViewModel.firePropertyChanged();
    }
}
