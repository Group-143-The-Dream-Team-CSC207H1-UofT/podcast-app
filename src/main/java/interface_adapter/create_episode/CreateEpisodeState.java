package interface_adapter.create_episode;

import entities.Episode;
import entities.Podcast;

public class CreateEpisodeState {
    private Episode episode = null;
    private Podcast currentPodcast;
    private String errorMessage = "";
    public CreateEpisodeState() {}

    public Episode getEpisode() {
        return episode;
    }
    public Podcast getCurrentPodcast(){return currentPodcast;}
    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
    public void setCurrentPodcast(Podcast podcast){this.currentPodcast = podcast;}
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
