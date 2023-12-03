package use_case.create_episode;

import data_access.EpisodeDataAccess;
import data_access.PodcastDataAccess;
import entities.Episode;
import entities.Podcast;

import java.util.UUID;

public class CreateEpisodeInteractor implements CreateEpisodeInputBoundary {
    private final CreateEpisodeOutputBoundary outputBoundary;
    private final EpisodeDataAccess episodeDAO;
    private final PodcastDataAccess podcastDAO;

    public CreateEpisodeInteractor(CreateEpisodeOutputBoundary outputBoundary, EpisodeDataAccess episodeDAO, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.episodeDAO = episodeDAO;
        this.podcastDAO = podcastDAO;
    }

    public void execute(CreateEpisodeInputData inputData) {
        UUID uniqueID = UUID.randomUUID();
        if (episodeDAO.saveFile(inputData.getAudioFileURI(), uniqueID)) {
            // the saving of the file succeeded.
            Episode episode = new Episode(uniqueID, inputData.getPodcastUUID(), inputData.getTitle(), inputData.getDescription(), null, null);
            episodeDAO.saveEpisode(episode);
            Podcast podcast = podcastDAO.getPodcastById(inputData.getPodcastUUID());
            podcast.addMediaItem(episode);
            podcastDAO.savePodcast(podcast);
            outputBoundary.prepareSuccessView(new CreateEpisodeOutputData(episode, podcast));
        } else {
            outputBoundary.prepareFailView("Failed to create episode.");
        }
    }
}
