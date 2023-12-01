package use_case.display_podcast;

import java.util.UUID;

public class DisplayPodcastInputData {
    final private UUID podcastID;

    public DisplayPodcastInputData(UUID podcastID) {
        this.podcastID = podcastID;
    }

    public UUID getPodcastID() {
        return podcastID;
    }
}
