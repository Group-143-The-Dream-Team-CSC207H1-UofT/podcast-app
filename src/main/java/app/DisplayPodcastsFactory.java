package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcast.DisplayPodcastController;
import interface_adapter.display_podcast.DisplayPodcastPresenter;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import interface_adapter.display_podcasts.DisplayPodcastsController;
import interface_adapter.display_podcasts.DisplayPodcastsPresenter;
import interface_adapter.display_podcasts.DisplayPodcastsViewModel;
import use_case.display_podcast.DisplayPodcastInputBoundary;
import use_case.display_podcast.DisplayPodcastInteractor;
import use_case.display_podcast.DisplayPodcastOutputBoundary;
import use_case.display_podcasts.DisplayPodcastsInputBoundary;
import use_case.display_podcasts.DisplayPodcastsInteractor;
import use_case.display_podcasts.DisplayPodcastsOutputBoundary;
import view.DisplayPodcastsView;

public class DisplayPodcastsFactory {
    public static DisplayPodcastsView create(ViewManagerModel viewManagerModel, DisplayPodcastsViewModel displayPodcastsViewModel, DisplayPodcastViewModel displayPodcastViewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastsController displayPodcastsController = DisplayPodcastsFactory.createDisplayPodcastsUseCase(viewManagerModel, displayPodcastsViewModel, podcastDAO);
        DisplayPodcastController  displayPodcastController = DisplayPodcastsFactory.createDisplayPodcastUseCase(viewManagerModel, displayPodcastViewModel, displayPodcastsViewModel, podcastDAO);
        return new DisplayPodcastsView(displayPodcastsController, displayPodcastController, displayPodcastsViewModel);
    }
    private static DisplayPodcastsController createDisplayPodcastsUseCase(ViewManagerModel viewManagerModel, DisplayPodcastsViewModel viewModel, PodcastDataAccess podcastDAO) {
        DisplayPodcastsOutputBoundary displayPodcastsPresenter = new DisplayPodcastsPresenter(viewModel, viewManagerModel);
        DisplayPodcastsInputBoundary displayPodcastsInteractor = new DisplayPodcastsInteractor(displayPodcastsPresenter, podcastDAO);
        return new DisplayPodcastsController(displayPodcastsInteractor);
    }

    private static DisplayPodcastController createDisplayPodcastUseCase(ViewManagerModel viewManagerModel, DisplayPodcastViewModel viewModel, DisplayPodcastsViewModel displayPodcastsViewModel, PodcastDataAccess podcastDAO){
        DisplayPodcastOutputBoundary displayPodcastOutputBoundary = new DisplayPodcastPresenter(viewModel, displayPodcastsViewModel, viewManagerModel);
        DisplayPodcastInputBoundary displayPodcastInteractor = new DisplayPodcastInteractor(displayPodcastOutputBoundary, podcastDAO);
        return new DisplayPodcastController(displayPodcastInteractor);
    }
}
