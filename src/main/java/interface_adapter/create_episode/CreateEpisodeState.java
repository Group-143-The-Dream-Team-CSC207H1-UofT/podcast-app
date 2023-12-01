package interface_adapter.create_episode;

import entities.Episode;
import entities.Podcast;

import java.util.UUID;

public class CreateEpisodeState {
    private Episode episode = null;
    private UUID currentPodcastId = null;
    private String errorMessage = "";
    public CreateEpisodeState() {}

    public Episode getEpisode() {
        return episode;
    }
    public UUID getCurrentPodcastId(){return currentPodcastId;}
    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
    public void setCurrentPodcastId(UUID podcastId){this.currentPodcastId = podcastId;}
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
