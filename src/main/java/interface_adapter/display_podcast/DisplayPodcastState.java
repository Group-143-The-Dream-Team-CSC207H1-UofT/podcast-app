package interface_adapter.display_podcast;

import entities.Podcast;

public class DisplayPodcastState {

    private Podcast currentPodcast;
    private String errorMessage = "";


    public DisplayPodcastState(Podcast currentPodcast) {
        this.currentPodcast = currentPodcast;
    }
    public DisplayPodcastState() {}
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
