package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.Podcast;

import java.util.List;

public class DisplayPodcastsOutputData {
    private final List<Podcast> allPodcasts;
    private final boolean useCaseFailed;
    public DisplayPodcastsOutputData(List<Podcast> allPodcasts, boolean useCaseFailed) {
        this.allPodcasts = allPodcasts;
        this.useCaseFailed = useCaseFailed;
    }
    public List<Podcast> getAllPodcasts() {
        return allPodcasts;
    }
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
