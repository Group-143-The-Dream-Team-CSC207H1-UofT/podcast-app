package use_case.episode;

import data_access.EpisodeDataAccess;
import data_access.PodcastDataAccess;
import entities.Episode;
import entities.Podcast;

import java.util.UUID;

public class EpisodeInteractor implements EpisodeInputBoundary {

    private final EpisodeDataAccess episodeDataAccessObject;
    private final EpisodeOutputBoundary displayEpisodePresenter;

    private final PodcastDataAccess podcastDataAccessObject;

    public EpisodeInteractor(EpisodeDataAccess episodeDataAccessObject, PodcastDataAccess podcastDataAccessObject, EpisodeOutputBoundary displayEpisodePresenter){
        this.displayEpisodePresenter = displayEpisodePresenter;
        this.episodeDataAccessObject = episodeDataAccessObject;
        this.podcastDataAccessObject = podcastDataAccessObject;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(EpisodeInputData episodeInputData) {
        UUID episodeUUID = episodeInputData.getEpisodeId();
        // TODO: if we want to add abstarction we need to use MediaItem instead of Episode here
        Episode episode = episodeDataAccessObject.getEpisodeById(episodeUUID);
        if (episode == null) {
            displayEpisodePresenter.prepareFailView("Episode with ID " + episodeUUID + " does not exist");
        } else {
            Podcast podcast = podcastDataAccessObject.getPodcastById(episode.getPodcastUUID());
        EpisodeOutputData episodeOutputData = new EpisodeOutputData(episode, podcast, episodeInputData.getCurrentTextChunk(), false);
        displayEpisodePresenter.prepareSuccessView(episodeOutputData);
        }
    }
}
