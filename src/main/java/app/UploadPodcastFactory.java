package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcast.DisplayPodcastController;
import interface_adapter.display_podcast.DisplayCreatePodcastPresenter;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import use_case.create_podcast.CreatePodcastInputBoundary;
import use_case.create_podcast.CreatePodcastInteractor;
import use_case.create_podcast.CreatePodcastOutputBoundary;
import view.UploadPodcastView;

public class UploadPodcastFactory {
    public static UploadPodcastView create(ViewManagerModel viewManagerModel, DisplayPodcastViewModel viewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastController controller = UploadPodcastFactory.createUploadPodcastUseCase(viewManagerModel, viewModel, podcastDAO);
        return new UploadPodcastView(controller, viewModel);
    }
    private static DisplayPodcastController createUploadPodcastUseCase(ViewManagerModel viewManagerModel, DisplayPodcastViewModel viewModel, PodcastDataAccess podcastDAO) {
        CreatePodcastOutputBoundary presenter = new DisplayCreatePodcastPresenter(viewModel, viewManagerModel);
        CreatePodcastInputBoundary podcastInteractor = new CreatePodcastInteractor(presenter, podcastDAO);
        return new DisplayPodcastController(podcastInteractor);
    }
}
