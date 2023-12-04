package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
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
    /**
     * Factory method to create and configure an instance of HomeView.
     * This method initializes controllers viewing the home screen and displaying a podcast.
     * @param viewManagerModel The model managing different views.
     * @param homeViewModel The model managing displaying all created podcasts.
     * @param podcastViewModel The model managing displaying a podcast.
     * @param createEpisodeViewModel The model managing displaying an episode.
     * @param podcastDAO The Data Access Object for reading and storing podcasts.
     * @return An instance of the HomeView.
     */
    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, PodcastViewModel podcastViewModel, CreateEpisodeViewModel createEpisodeViewModel, PodcastDataAccess podcastDAO) {
        HomeController homeController = HomeViewFactory.createHomeUseCase(viewManagerModel, homeViewModel, podcastDAO);
        PodcastController podcastController = HomeViewFactory.createPodcastUseCase(viewManagerModel, podcastViewModel, homeViewModel, createEpisodeViewModel, podcastDAO);
        return new HomeView(homeController, podcastController, homeViewModel, viewManagerModel);
    }
    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel viewModel, PodcastDataAccess podcastDAO) {
        HomeOutputBoundary displayPodcastsPresenter = new HomePresenter(viewModel, viewManagerModel);
        HomeInputBoundary displayPodcastsInteractor = new HomeInteractor(displayPodcastsPresenter, podcastDAO);
        return new HomeController(displayPodcastsInteractor);
    }

    private static PodcastController createPodcastUseCase(ViewManagerModel viewManagerModel, PodcastViewModel viewModel, HomeViewModel homeViewModel, CreateEpisodeViewModel createEpisodeViewModel, PodcastDataAccess podcastDAO){
        PodcastOutputBoundary podcastOutputBoundary = new PodcastPresenter(viewModel, createEpisodeViewModel, homeViewModel, viewManagerModel);
        PodcastInputBoundary displayPodcastInteractor = new PodcastInteractor(podcastOutputBoundary, podcastDAO);
        return new PodcastController(displayPodcastInteractor);
    }
}
