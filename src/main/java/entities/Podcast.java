package entities;

import java.util.List;

public class Podcast extends MediaCollection {
    private User createdBy;

    public Podcast(int id, String podcastName, User createdBy, List<MediaItem> episodes) {
        super(id, podcastName, episodes);
        this.createdBy = createdBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }
}

