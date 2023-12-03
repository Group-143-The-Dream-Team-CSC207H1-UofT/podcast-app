package use_case.home;

import entities.Podcast;

import java.util.List;

public class HomeOutputData {
    private final List<Podcast> allPodcasts;
    private String error;
    private final boolean useCaseFailed;
    public HomeOutputData(List<Podcast> allPodcasts, String error, boolean useCaseFailed) {
        this.allPodcasts = allPodcasts;
        this.error = error;
        this.useCaseFailed = useCaseFailed;
    }
    public List<Podcast> getAllPodcasts() {
        return allPodcasts;
    }

    public String getError() {
        return error;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
