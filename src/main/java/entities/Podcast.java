package entities;

import java.util.List;
import java.util.UUID;

public class Podcast extends MediaCollection {
    private User createdBy;

    public Podcast(UUID id, String podcastName, User createdBy, List<MediaItem> episodes) {
        super(id, podcastName, episodes);
        this.createdBy = createdBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }
}

