package app;

import data_access.EpisodeDataAccess;
import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
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

    public static PodcastView create(
            ViewManagerModel viewManagerModel,
            PodcastViewModel podcastViewModel,
            EpisodeViewModel episodeViewModel,
            HomeViewModel homeViewModel,
            EpisodeDataAccess episodeDataAccessObject,
            PodcastDataAccess podcastDAO
    ){
            EpisodeController episodeController = createDisplayEpisodeUseCase(viewManagerModel, podcastViewModel, episodeViewModel, episodeDataAccessObject);
            HomeController homeController = createHomeUseCase(viewManagerModel, homeViewModel, podcastDAO);
            return new PodcastView(viewManagerModel, podcastViewModel, episodeController, homeController);
    }

    private static EpisodeController createDisplayEpisodeUseCase(
            ViewManagerModel viewManagerModel,
            PodcastViewModel podcastViewModel,
            EpisodeViewModel episodeViewModel,
            EpisodeDataAccess episodeDataAccessObject
    ) {
        EpisodeOutputBoundary episodeOutputBoundary = new EpisodePresenter(viewManagerModel, episodeViewModel, podcastViewModel);
        EpisodeInputBoundary displayEpisodeInteractor = new EpisodeInteractor(episodeDataAccessObject, episodeOutputBoundary);
        return new EpisodeController(displayEpisodeInteractor);
    }

    private static HomeController createHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel viewModel, PodcastDataAccess podcastDAO) {
        HomeOutputBoundary displayPodcastsPresenter = new HomePresenter(viewModel, viewManagerModel);
        HomeInputBoundary displayPodcastsInteractor = new HomeInteractor(displayPodcastsPresenter, podcastDAO);
        return new HomeController(displayPodcastsInteractor);
    }
}
