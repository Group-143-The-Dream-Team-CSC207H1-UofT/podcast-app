package interface_adapter.display_podcast;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcasts.DisplayPodcastsState;
import interface_adapter.display_podcasts.DisplayPodcastsViewModel;
import use_case.create_podcast.CreatePodcastOutputBoundary;
import use_case.create_podcast.CreatePodcastOutputData;
import use_case.display_podcast.DisplayPodcastOutputBoundary;
import use_case.display_podcast.DisplayPodcastOutputData;
import use_case.display_podcasts.DisplayPodcastsOutputBoundary;
import use_case.display_podcasts.DisplayPodcastsOutputData;
import view.DisplayPodcastsView;


public class DisplayPodcastPresenter implements DisplayPodcastOutputBoundary {

    private final DisplayPodcastViewModel displayPodcastViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DisplayPodcastsViewModel displayPodcastsViewModel;

    public DisplayPodcastPresenter(DisplayPodcastViewModel displayPodcastViewModel, DisplayPodcastsViewModel displayPodcastsViewModel, ViewManagerModel viewManagerModel) {
        this.displayPodcastViewModel = displayPodcastViewModel;
        this.viewManagerModel = viewManagerModel;
        this.displayPodcastsViewModel = displayPodcastsViewModel;
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
        DisplayPodcastsState currentState = this.displayPodcastsViewModel.getState();
        currentState.setErrorMessage(error);
        this.displayPodcastsViewModel.firePropertyChanged();
    }
}
