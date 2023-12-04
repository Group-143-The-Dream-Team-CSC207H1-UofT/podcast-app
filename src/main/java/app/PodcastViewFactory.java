package app;

import data_access.EpisodeDataAccess;
import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.episode.EpisodeController;
import interface_adapter.episode.EpisodePresenter;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import use_case.episode.EpisodeInputBoundary;
import use_case.episode.EpisodeInteractor;
import use_case.episode.EpisodeOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import view.PodcastView;

public class PodcastViewFactory {
    /**
     * Factory method to create and configure a PodcastView instance.
     * This method assembles the necessary components and controllers required for the PodcastView.
     *
     * @param viewManagerModel The model managing different views in the application.
     * @param podcastViewModel ViewModel associated with podcast-related operations.
     * @param episodeViewModel ViewModel for episode-related functionalities.
     * @param createEpisodeViewModel ViewModel for creating new episodes.
     * @param homeViewModel ViewModel for the home view of the application.
     * @param episodeDataAccessObject Data access object for episode-related operations.
     * @param podcastDAO Data access object for podcast-related operations.
     * @return An instance of PodcastView fully configured with the necessary controllers and view models.
     */
    public static PodcastView create(
            ViewManagerModel viewManagerModel,
            PodcastViewModel podcastViewModel,
            EpisodeViewModel episodeViewModel,
            CreateEpisodeViewModel createEpisodeViewModel,
            HomeViewModel homeViewModel,
            PodcastDataAccess podcastDataAccessObject,
            EpisodeDataAccess episodeDataAccessObject,
            PodcastDataAccess podcastDAO
    ){
            EpisodeController episodeController = createDisplayEpisodeUseCase(viewManagerModel, podcastViewModel, episodeViewModel, podcastDataAccessObject, episodeDataAccessObject);
            HomeController homeController = createHomeUseCase(viewManagerModel, homeViewModel, podcastDAO);
            return new PodcastView(viewManagerModel, podcastViewModel, createEpisodeViewModel, episodeController, homeController);
    }

    private static EpisodeController createDisplayEpisodeUseCase(
            ViewManagerModel viewManagerModel,
            PodcastViewModel podcastViewModel,
            EpisodeViewModel episodeViewModel,
            PodcastDataAccess podcastDataAccessObject,
            EpisodeDataAccess episodeDataAccessObject
    ) {
        EpisodeOutputBoundary episodeOutputBoundary = new EpisodePresenter(viewManagerModel, episodeViewModel, podcastViewModel);
        EpisodeInputBoundary displayEpisodeInteractor = new EpisodeInteractor(episodeDataAccessObject, podcastDataAccessObject, episodeOutputBoundary);
        return new EpisodeController(displayEpisodeInteractor);
    }

    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel viewModel, PodcastDataAccess podcastDAO) {
        HomeOutputBoundary displayPodcastsPresenter = new HomePresenter(viewModel, viewManagerModel);
        HomeInputBoundary displayPodcastsInteractor = new HomeInteractor(displayPodcastsPresenter, podcastDAO);
        return new HomeController(displayPodcastsInteractor);
    }
}
