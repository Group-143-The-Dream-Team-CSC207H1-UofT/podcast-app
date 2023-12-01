package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcast.DisplayPodcastController;
import interface_adapter.display_podcast.DisplayPodcastPresenter;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import use_case.display_podcast.DisplayPodcastInputBoundary;
import use_case.display_podcast.DisplayPodcastInteractor;
import use_case.display_podcast.DisplayPodcastOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import view.HomeView;

public class DisplayPodcastsFactory {
    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, DisplayPodcastViewModel displayPodcastViewModel, PodcastDataAccess podcastDAO) {
        HomeController homeController = DisplayPodcastsFactory.createDisplayPodcastsUseCase(viewManagerModel, homeViewModel, podcastDAO);
        DisplayPodcastController  displayPodcastController = DisplayPodcastsFactory.createDisplayPodcastUseCase(viewManagerModel, displayPodcastViewModel, homeViewModel, podcastDAO);
        return new HomeView(homeController, displayPodcastController, homeViewModel, viewManagerModel);
    }
    private static HomeController createDisplayPodcastsUseCase(ViewManagerModel viewManagerModel, HomeViewModel viewModel, PodcastDataAccess podcastDAO) {
        HomeOutputBoundary displayPodcastsPresenter = new HomePresenter(viewModel, viewManagerModel);
        HomeInputBoundary displayPodcastsInteractor = new HomeInteractor(displayPodcastsPresenter, podcastDAO);
        return new HomeController(displayPodcastsInteractor);
    }

    private static DisplayPodcastController createDisplayPodcastUseCase(ViewManagerModel viewManagerModel, DisplayPodcastViewModel viewModel, HomeViewModel homeViewModel, PodcastDataAccess podcastDAO){
        DisplayPodcastOutputBoundary displayPodcastOutputBoundary = new DisplayPodcastPresenter(viewModel, homeViewModel, viewManagerModel);
        DisplayPodcastInputBoundary displayPodcastInteractor = new DisplayPodcastInteractor(displayPodcastOutputBoundary, podcastDAO);
        return new DisplayPodcastController(displayPodcastInteractor);
    }
}
