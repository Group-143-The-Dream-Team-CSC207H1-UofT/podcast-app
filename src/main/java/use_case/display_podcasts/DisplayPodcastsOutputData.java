package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.Podcast;

import java.util.List;

public class DisplayPodcastsOutputData {
    private final List<Podcast> allPodcasts;
    private String error;
    private final boolean useCaseFailed;
    public DisplayPodcastsOutputData(List<Podcast> allPodcasts, String error, boolean useCaseFailed) {
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
