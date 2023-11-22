package use_case.display_episode;

import data_access.EpisodeDataAccess;
import entities.Episode;

import java.util.UUID;

public class DisplayEpisodeInteractor implements DisplayEpisodeInputBoundary{

    final EpisodeDataAccess episodeDataAccessObject;
    final DisplayEpisodeOutputBoundary displayEpisodePresenter;

    public DisplayEpisodeInteractor(EpisodeDataAccess episodeDataAccessObject, DisplayEpisodeOutputBoundary displayEpisodePresenter){
        this.displayEpisodePresenter = displayEpisodePresenter;
        this.episodeDataAccessObject = episodeDataAccessObject;
    }

    @Override
    public void execute(DisplayEpisodeInputData displayEpisodeInputData) {
        UUID episodeUUID = displayEpisodeInputData.getEpisodeId();
        // TODO: if we want to add abstarction we need to use MediaItem instead of Episode here
        Episode episode = episodeDataAccessObject.getEpisodeById(episodeUUID);
        if (episode == null) {
            displayEpisodePresenter.prepareFailView("Episode with ID " + episodeUUID + " does not exist");
        } else {
        DisplayEpisodeOutputData displayEpisodeOutputData = new DisplayEpisodeOutputData(episode, displayEpisodeInputData.getCurrentTextChunkIndex(), false);
        displayEpisodePresenter.prepareSuccessView(displayEpisodeOutputData);
        }
    }
}
