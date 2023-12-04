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

    /**
     * Constructs an EpisodePresenter with necessary models and view models.
     *
     * @param viewManagerModel The model managing different views.
     * @param episodeViewModel ViewModel for episode-related functionalities.
     * @param podcastViewModel ViewModel associated with podcast-related operations.
     */
    public EpisodePresenter(ViewManagerModel viewManagerModel, EpisodeViewModel episodeViewModel, PodcastViewModel podcastViewModel){
        this.episodeViewModel = episodeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.podcastViewModel = podcastViewModel;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public void prepareSuccessView(EpisodeOutputData episodeOutputData) {
        EpisodeState episodeState = episodeViewModel.getState();
        PodcastState podcastState = podcastViewModel.getState();
        episodeState.setCurrentEpisode(episodeOutputData.getEpisode());
        episodeState.setTextChunks(episodeOutputData.getEpisode().getTranscript().getTextChunks());
        episodeState.setCurrentTextChunk(episodeOutputData.getCurrentTextChunk());
        podcastState.setCurrentPodcast(episodeOutputData.getPodcast());
        this.episodeViewModel.setState(episodeState);
        this.podcastViewModel.setState(podcastState);
        this.episodeViewModel.firePropertyChanged();
        this.podcastViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(episodeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public void prepareFailView(String error) {
        PodcastState podcastState = podcastViewModel.getState();
        podcastState.setErrorMessage(error);
        podcastViewModel.firePropertyChanged();
    }
}
