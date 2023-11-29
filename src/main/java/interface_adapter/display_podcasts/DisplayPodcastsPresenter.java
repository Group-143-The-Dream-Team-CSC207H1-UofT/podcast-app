package interface_adapter.display_podcasts;

import interface_adapter.ViewManagerModel;
import use_case.display_podcasts.DisplayPodcastsOutputBoundary;
import use_case.display_podcasts.DisplayPodcastsOutputData;

public class DisplayPodcastsPresenter implements DisplayPodcastsOutputBoundary {
    private DisplayPodcastsViewModel displayPodcastsViewModel;
    private ViewManagerModel viewManagerModel;
    public DisplayPodcastsPresenter(DisplayPodcastsViewModel displayPodcastsViewModel, ViewManagerModel viewManagerModel){
        this.displayPodcastsViewModel = displayPodcastsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(DisplayPodcastsOutputData outputData) {
        DisplayPodcastsState currentState = displayPodcastsViewModel.getState();
        currentState.setAllPodcasts(outputData.getAllPodcasts());
        displayPodcastsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(DisplayPodcastsOutputData outputData) {
        return; // This method is never actually used
    }
}
