package use_case.podcast;

import java.util.UUID;

public class PodcastInputData {
    final private UUID podcastID;

    public PodcastInputData(UUID podcastID) {
        this.podcastID = podcastID;
    }

    public UUID getPodcastID() {
        return podcastID;
    }
}
