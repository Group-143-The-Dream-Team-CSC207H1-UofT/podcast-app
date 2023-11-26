package interface_adapter.display_episode;

import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;
import use_case.display_episode.DisplayEpisodeOutputBoundary;
import use_case.display_episode.DisplayEpisodeOutputData;

public class DisplayEpisodePresenter implements DisplayEpisodeOutputBoundary {

    private final DisplayEpisodeViewModel displayEpisodeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PodcastViewModel podcastViewModel;

    public DisplayEpisodePresenter(ViewManagerModel viewManagerModel, DisplayEpisodeViewModel displayEpisodeViewModel, PodcastViewModel podcastViewModel){
        this.displayEpisodeViewModel = displayEpisodeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.podcastViewModel = podcastViewModel;
    }
    @Override
    public void prepareSuccessView(DisplayEpisodeOutputData response) {
        DisplayEpisodeState displayEpisodeState = displayEpisodeViewModel.getState();
        displayEpisodeState.setCurrentEpisode(response.getEpisode());
        displayEpisodeState.setTextChunks(response.getEpisode().getTranscript().getTextChunks());
        displayEpisodeState.setCurrentTextChunkIndex(response.getCurrentTextChunkIndex());
        this.displayEpisodeViewModel.setState(displayEpisodeState);
        this.displayEpisodeViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(displayEpisodeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        PodcastState podcastState = podcastViewModel.getState();
        podcastState.setEpisodeError(error);
        podcastViewModel.firePropertyChanged();
    }
}
