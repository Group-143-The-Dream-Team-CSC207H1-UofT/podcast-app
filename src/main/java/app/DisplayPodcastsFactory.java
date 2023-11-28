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
    /**
     * Creates a new instance of DisplayPodcastsView
     * @param viewManagerModel the viewManagerModel that controls the views
     * @param viewModel an instance of DisplayPodcastsViewModel that has the state of the view
     * @param podcastDAO a data access object for accessing podcasts
     * @return a new instance of DisplayPodcastsView that is connected to the parameters
     */
    public static DisplayPodcastsView create(ViewManagerModel viewManagerModel, DisplayPodcastsViewModel viewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastsController controller = DisplayPodcastsFactory.createDisplayPodcastsUseCase(viewManagerModel, viewModel, podcastDAO);
        return new DisplayPodcastsView(controller, viewModel);
    }
    private static DisplayPodcastsController createDisplayPodcastsUseCase(ViewManagerModel viewManagerModel, DisplayPodcastsViewModel viewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastsOutputBoundary displayPodcastsPresenter = new DisplayPodcastsPresenter(viewModel, viewManagerModel);
        DisplayPodcastsInputBoundary displayPodcastsInteractor = new DisplayPodcastsInteractor(displayPodcastsPresenter, podcastDAO);
        return new DisplayPodcastsController(displayPodcastsInteractor);
    }
}
