package interface_adapter.display_podcast;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.display_podcast.DisplayPodcastOutputBoundary;
import use_case.display_podcast.DisplayPodcastOutputData;


public class DisplayPodcastPresenter implements DisplayPodcastOutputBoundary {

    private final DisplayPodcastViewModel displayPodcastViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public DisplayPodcastPresenter(DisplayPodcastViewModel displayPodcastViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
        this.displayPodcastViewModel = displayPodcastViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayPodcastOutputData podcastData) {
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
        HomeState currentState = this.homeViewModel.getState();
        currentState.setErrorMessage(error);
        this.homeViewModel.firePropertyChanged();
    }
}
