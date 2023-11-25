package entities;

import java.util.List;
import java.util.UUID;

public abstract class MediaCollection {
    private final UUID id;
    private final String name;
    private List<MediaItem> items;

    // Constructors, getters ...
    public MediaCollection(UUID id, String mediaName, List<MediaItem> mediaItems) {
        this.id = id;
        this.name = mediaName;
        this.items = List.copyOf(mediaItems);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MediaItem> getItems() {
        return items;
    }

    public void addMediaItem(MediaItem item) {
        items.add(item);
    }
}

