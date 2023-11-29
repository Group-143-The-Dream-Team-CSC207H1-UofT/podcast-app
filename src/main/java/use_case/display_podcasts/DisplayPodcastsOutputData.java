package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.Podcast;

import java.util.List;

public class DisplayPodcastsOutputData {
    private final List<Podcast> allPodcasts;
    public DisplayPodcastsOutputData(List<Podcast> allPodcasts, String error, boolean useCaseFailed) {
        this.allPodcasts = allPodcasts;
    }
    public List<Podcast> getAllPodcasts() {
        return allPodcasts;
    }

}
