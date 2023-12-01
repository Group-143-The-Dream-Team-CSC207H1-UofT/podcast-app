package interface_adapter.episode;

import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;
import use_case.episode.EpisodeOutputBoundary;
import use_case.episode.EpisodeOutputData;

public class EpisodePresenter implements EpisodeOutputBoundary {

    private final EpisodeViewModel episodeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PodcastViewModel podcastViewModel;

    public EpisodePresenter(ViewManagerModel viewManagerModel, EpisodeViewModel episodeViewModel, PodcastViewModel podcastViewModel){
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
        PodcastState podcastState = podcastViewModel.getState();
        podcastState.setErrorMessage(error);
        podcastViewModel.firePropertyChanged();
    }
}
