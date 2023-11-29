package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcasts.DisplayPodcastsController;
import interface_adapter.display_podcasts.DisplayPodcastsPresenter;
import interface_adapter.display_podcasts.DisplayPodcastsViewModel;
import use_case.display_podcasts.DisplayPodcastsInputBoundary;
import use_case.display_podcasts.DisplayPodcastsInteractor;
import use_case.display_podcasts.DisplayPodcastsOutputBoundary;
import view.DisplayPodcastsView;

public class DisplayPodcastsFactory {
    public static DisplayPodcastsView create(ViewManagerModel viewManagerModel, DisplayPodcastsViewModel viewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastsController controller = DisplayPodcastsFactory.createDisplayPodcastsUseCase(viewManagerModel, viewModel, podcastDAO);
        return new DisplayPodcastsView(controller, viewModel, viewManagerModel);
    }
    private static DisplayPodcastsController createDisplayPodcastsUseCase(ViewManagerModel viewManagerModel, DisplayPodcastsViewModel viewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastsOutputBoundary displayPodcastsPresenter = new DisplayPodcastsPresenter(viewModel, viewManagerModel);
        DisplayPodcastsInputBoundary displayPodcastsInteractor = new DisplayPodcastsInteractor(displayPodcastsPresenter, podcastDAO);
        return new DisplayPodcastsController(displayPodcastsInteractor);
    }
}
