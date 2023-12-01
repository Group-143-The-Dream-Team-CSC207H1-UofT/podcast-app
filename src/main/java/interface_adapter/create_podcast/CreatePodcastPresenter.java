package interface_adapter.create_podcast;

import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;
import use_case.create_podcast.CreatePodcastOutputBoundary;
import use_case.create_podcast.CreatePodcastOutputData;

public class CreatePodcastPresenter implements CreatePodcastOutputBoundary {
    private final CreatePodcastViewModel viewModel;
    private final PodcastViewModel podcastViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreatePodcastPresenter(CreatePodcastViewModel viewModel, PodcastViewModel podcastViewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.podcastViewModel = podcastViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreatePodcastOutputData outputData) {
        PodcastState state = podcastViewModel.getState();
        state.setCurrentPodcast(outputData.getPodcast());
        podcastViewModel.setState(state);
        podcastViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(podcastViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO: implement error messages
    }
}
