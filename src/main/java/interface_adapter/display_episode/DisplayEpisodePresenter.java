package interface_adapter.display_episode;

import interface_adapter.ViewManagerModel;
import use_case.display_episode.DisplayEpisodeOutputBoundary;
import use_case.display_episode.DisplayEpisodeOutputData;

public class DisplayEpisodePresenter implements DisplayEpisodeOutputBoundary {

    private final DisplayEpisodeViewModel displayEpisodeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayEpisodePresenter(DisplayEpisodeViewModel displayEpisodeViewModel, ViewManagerModel viewManagerModel){
        this.displayEpisodeViewModel = displayEpisodeViewModel;
        this.viewManagerModel = viewManagerModel;
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
        // TODO : set the podcast view's state including the error
    }
}
