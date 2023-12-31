package entities;

import java.util.List;
import java.util.UUID;

public class Podcast extends MediaCollection {
    private User assignedTo;

    public Podcast(UUID id, String podcastName, String description, User assignedTo, List<MediaItem> episodes) {
        super(id, podcastName, description, episodes);
        this.assignedTo = assignedTo;
    }

    public User getAssignedTo() {
        return assignedTo;
    }
}

