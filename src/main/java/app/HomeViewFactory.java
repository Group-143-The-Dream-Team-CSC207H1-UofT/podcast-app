package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastController;
import interface_adapter.podcast.PodcastPresenter;
import interface_adapter.podcast.PodcastViewModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import use_case.podcast.PodcastInputBoundary;
import use_case.podcast.PodcastInteractor;
import use_case.podcast.PodcastOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import view.HomeView;

public class HomeViewFactory {
    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, PodcastViewModel podcastViewModel, PodcastDataAccess podcastDAO) {
        HomeController homeController = HomeViewFactory.createHomeUseCase(viewManagerModel, homeViewModel, podcastDAO);
        PodcastController podcastController = HomeViewFactory.createPodcastUseCase(viewManagerModel, podcastViewModel, homeViewModel, podcastDAO);
        return new HomeView(homeController, podcastController, homeViewModel, viewManagerModel);
    }
    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel viewModel, PodcastDataAccess podcastDAO) {
        HomeOutputBoundary displayPodcastsPresenter = new HomePresenter(viewModel, viewManagerModel);
        HomeInputBoundary displayPodcastsInteractor = new HomeInteractor(displayPodcastsPresenter, podcastDAO);
        return new HomeController(displayPodcastsInteractor);
    }

    private static PodcastController createPodcastUseCase(ViewManagerModel viewManagerModel, PodcastViewModel viewModel, HomeViewModel homeViewModel, PodcastDataAccess podcastDAO){
        PodcastOutputBoundary podcastOutputBoundary = new PodcastPresenter(viewModel, homeViewModel, viewManagerModel);
        PodcastInputBoundary displayPodcastInteractor = new PodcastInteractor(podcastOutputBoundary, podcastDAO);
        return new PodcastController(displayPodcastInteractor);
    }
}
