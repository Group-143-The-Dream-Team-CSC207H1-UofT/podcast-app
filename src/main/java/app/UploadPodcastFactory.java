package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastController;
import interface_adapter.podcast.PodcastPresenter;
import interface_adapter.podcast.PodcastViewModel;
import use_case.podcast.PodcastInputBoundary;
import use_case.podcast.PodcastInteractor;
import use_case.podcast.PodcastOutputBoundary;
import view.UploadPodcastView;
import view.UploadView;

public class UploadPodcastFactory {
    public static UploadPodcastView create(ViewManagerModel viewManagerModel, PodcastViewModel viewModel, PodcastDataAccess podcastDAO) {
        PodcastController controller = UploadPodcastFactory.createUploadPodcastUseCase(viewManagerModel, viewModel, podcastDAO);
        return new UploadPodcastView(controller, viewModel);
    }
    private static PodcastController createUploadPodcastUseCase(ViewManagerModel viewManagerModel, PodcastViewModel viewModel, PodcastDataAccess podcastDAO) {
        PodcastOutputBoundary presenter = new PodcastPresenter(viewModel, viewManagerModel);
        PodcastInputBoundary podcastInteractor = new PodcastInteractor(presenter, podcastDAO);
        return new PodcastController(podcastInteractor);
    }
}
