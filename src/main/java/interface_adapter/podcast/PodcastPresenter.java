package interface_adapter.podcast;

import interface_adapter.ViewManagerModel;
import use_case.podcast.PodcastOutputBoundary;
import use_case.podcast.PodcastOutputData;


public class PodcastPresenter implements PodcastOutputBoundary {

    private final PodcastViewModel podcastViewModel;
    private final ViewManagerModel viewManagerModel;

    public PodcastPresenter(PodcastViewModel podcastViewModel, ViewManagerModel viewManagerModel) {
        this.podcastViewModel = podcastViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PodcastOutputData podcastData) {
        PodcastState currentState = this.podcastViewModel.getState();
        currentState.setPodcast(podcastData.getPodcast());
        currentState.setErrorMessage("");
        this.podcastViewModel.setState(currentState);
        this.podcastViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(podcastViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String error) {
        PodcastState currentState = this.podcastViewModel.getState();
        currentState.setPodcast(null);
        currentState.setErrorMessage(error);
        this.podcastViewModel.setState(currentState);
        this.podcastViewModel.firePropertyChanged();
    }
}
