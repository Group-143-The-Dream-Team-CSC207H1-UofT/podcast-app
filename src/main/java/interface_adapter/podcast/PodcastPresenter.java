package interface_adapter.podcast;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.podcast.PodcastOutputBoundary;
import use_case.podcast.PodcastOutputData;


public class PodcastPresenter implements PodcastOutputBoundary {

    private final PodcastViewModel podcastViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public PodcastPresenter(PodcastViewModel podcastViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
        this.podcastViewModel = podcastViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(PodcastOutputData podcastData) {
        PodcastState currentState = this.podcastViewModel.getState();
        currentState.setCurrentPodcast(podcastData.getPodcast());
        currentState.setErrorMessage("");
        this.podcastViewModel.setState(currentState);
        this.podcastViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(podcastViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String error) {
        HomeState currentState = this.homeViewModel.getState();
        currentState.setErrorMessage(error);
        this.homeViewModel.firePropertyChanged();
    }
}
