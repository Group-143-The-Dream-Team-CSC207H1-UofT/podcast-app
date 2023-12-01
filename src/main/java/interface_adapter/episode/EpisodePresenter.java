package interface_adapter.episode;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcast.DisplayPodcastState;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import use_case.episode.EpisodeOutputBoundary;
import use_case.episode.EpisodeOutputData;

public class EpisodePresenter implements EpisodeOutputBoundary {

    private final EpisodeViewModel episodeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DisplayPodcastViewModel podcastViewModel;

    public EpisodePresenter(ViewManagerModel viewManagerModel, EpisodeViewModel episodeViewModel, DisplayPodcastViewModel podcastViewModel){
        this.episodeViewModel = episodeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.podcastViewModel = podcastViewModel;
    }
    @Override
    public void prepareSuccessView(EpisodeOutputData response) {
        EpisodeState episodeState = episodeViewModel.getState();
        episodeState.setCurrentEpisode(response.getEpisode());
        episodeState.setTextChunks(response.getEpisode().getTranscript().getTextChunks());
        episodeState.setCurrentTextChunkIndex(response.getCurrentTextChunkIndex());
        this.episodeViewModel.setState(episodeState);
        this.episodeViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(episodeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        DisplayPodcastState podcastState = podcastViewModel.getState();
        podcastState.setErrorMessage(error);
        podcastViewModel.firePropertyChanged();
    }
}
