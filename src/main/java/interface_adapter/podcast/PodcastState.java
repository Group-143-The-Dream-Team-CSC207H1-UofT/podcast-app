package interface_adapter.podcast;

import entities.Podcast;

public class PodcastState {

    private Podcast currentPodcast;
    private String errorMessage = "";


    public PodcastState(Podcast currentPodcast) {
        this.currentPodcast = currentPodcast;
    }
    public PodcastState() {}
    public Podcast getCurrentPodcast() {
        return currentPodcast;
    }
    public void setCurrentPodcast(Podcast podcast) {
        this.currentPodcast = podcast;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
