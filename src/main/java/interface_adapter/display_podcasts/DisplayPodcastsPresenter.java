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

    /**
     * Displays the displayPodcastView after podcasts were successfully loaded
     * @param outputData the data passed in by the interactor which contains all podcasts
     */
    @Override
    public void prepareSuccessView(DisplayPodcastsOutputData outputData) {
        DisplayPodcastsState currentState = displayPodcastsViewModel.getState();
        currentState.setAllPodcasts(outputData.getAllPodcasts());
        displayPodcastsViewModel.firePropertyChanged();
    }

    /**
     * Displays the error after interactor was not successful in loading podcasts
     * @param outputData the data passed in by the interactor
     */
    @Override
    public void prepareFailView(DisplayPodcastsOutputData outputData) {
        DisplayPodcastsState currentState = new DisplayPodcastsState();
        currentState.setErrorMessage(outputData.getError());
        displayPodcastsViewModel.firePropertyChanged();
    }
}
