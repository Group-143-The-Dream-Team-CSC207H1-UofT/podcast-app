package interface_adapter.home;

import entities.Podcast;

import java.util.List;

public class HomeState {
    private List<Podcast> allPodcasts;
    private String errorMessage = "";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HomeState(List<Podcast> allPodcasts) {
        this.allPodcasts = allPodcasts;
    }
    public HomeState() {
        allPodcasts = null;
    }

    public List<Podcast> getAllPodcasts() {
        return allPodcasts;
    }

    public void setAllPodcasts(List<Podcast> allPodcasts) {
        this.allPodcasts = allPodcasts;
    }
}
