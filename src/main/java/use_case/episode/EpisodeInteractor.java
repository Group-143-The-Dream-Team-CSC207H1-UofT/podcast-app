package use_case.episode;

import data_access.EpisodeDataAccess;
import entities.Episode;

import java.util.UUID;

public class EpisodeInteractor implements EpisodeInputBoundary {

    private final EpisodeDataAccess episodeDataAccessObject;
    private final EpisodeOutputBoundary displayEpisodePresenter;

    public EpisodeInteractor(EpisodeDataAccess episodeDataAccessObject, EpisodeOutputBoundary displayEpisodePresenter){
        this.displayEpisodePresenter = displayEpisodePresenter;
        this.episodeDataAccessObject = episodeDataAccessObject;
    }

    @Override
    public void execute(EpisodeInputData episodeInputData) {
        UUID episodeUUID = episodeInputData.getEpisodeId();
        // TODO: if we want to add abstarction we need to use MediaItem instead of Episode here
        Episode episode = episodeDataAccessObject.getEpisodeById(episodeUUID);
        if (episode == null) {
            displayEpisodePresenter.prepareFailView("Episode with ID " + episodeUUID + " does not exist");
        } else {
        EpisodeOutputData episodeOutputData = new EpisodeOutputData(episode, episodeInputData.getCurrentTextChunkIndex(), false);
        displayEpisodePresenter.prepareSuccessView(episodeOutputData);
        }
    }
}
