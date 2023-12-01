package interface_adapter.display_podcasts;

import entities.Podcast;

import java.util.List;

public class DisplayPodcastsState {
    private List<Podcast> allPodcasts;
    private String errorMessage = "";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DisplayPodcastsState(List<Podcast> allPodcasts) {
        this.allPodcasts = allPodcasts;
    }
    public DisplayPodcastsState() {
        allPodcasts = null;
    }

    public List<Podcast> getAllPodcasts() {
        return allPodcasts;
    }

    public void setAllPodcasts(List<Podcast> allPodcasts) {
        this.allPodcasts = allPodcasts;
    }
}
