package app;

import data_access.EpisodeDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeController;
import interface_adapter.episode.EpisodePresenter;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import use_case.episode.EpisodeInputBoundary;
import use_case.episode.EpisodeInteractor;
import use_case.episode.EpisodeOutputBoundary;
import view.PodcastView;

public class PodcastViewFactory {

    public static PodcastView create(
            ViewManagerModel viewManagerModel,
            PodcastViewModel podcastViewModel,
            EpisodeViewModel episodeViewModel,
            EpisodeDataAccess episodeDataAccessObject
    ){
            EpisodeController episodeController = createDisplayEpisodeUseCase(viewManagerModel, podcastViewModel, episodeViewModel, episodeDataAccessObject);
            return new PodcastView(viewManagerModel, podcastViewModel, episodeController);
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
}
