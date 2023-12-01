package app;

import data_access.EpisodeDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_episode.DisplayEpisodeController;
import interface_adapter.display_episode.DisplayEpisodePresenter;
import interface_adapter.display_episode.DisplayEpisodeViewModel;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import use_case.display_episode.DisplayEpisodeInputBoundary;
import use_case.display_episode.DisplayEpisodeInteractor;
import use_case.display_episode.DisplayEpisodeOutputBoundary;
import view.PodcastView;

public class PodcastViewFactory {

    public static PodcastView create(
            ViewManagerModel viewManagerModel,
            DisplayPodcastViewModel podcastViewModel,
            DisplayEpisodeViewModel episodeViewModel,
            EpisodeDataAccess episodeDataAccessObject
    ){
            DisplayEpisodeController displayEpisodeController = createDisplayEpisodeUseCase(viewManagerModel, podcastViewModel, episodeViewModel, episodeDataAccessObject);
            return new PodcastView(podcastViewModel, displayEpisodeController);
    }

    private static DisplayEpisodeController createDisplayEpisodeUseCase(
            ViewManagerModel viewManagerModel,
            DisplayPodcastViewModel podcastViewModel,
            DisplayEpisodeViewModel episodeViewModel,
            EpisodeDataAccess episodeDataAccessObject
    ) {
        DisplayEpisodeOutputBoundary displayEpisodeOutputBoundary = new DisplayEpisodePresenter(viewManagerModel, episodeViewModel, podcastViewModel);
        DisplayEpisodeInputBoundary displayEpisodeInteractor = new DisplayEpisodeInteractor(episodeDataAccessObject, displayEpisodeOutputBoundary);
        return new DisplayEpisodeController(displayEpisodeInteractor);
    }
}
