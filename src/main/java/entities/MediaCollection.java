package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class MediaCollection {
    private final UUID id;
    private final String name;
    private final String description;
    private List<MediaItem> items;

    // Constructors, getters ...
    public MediaCollection(UUID id, String mediaName, String description, List<MediaItem> mediaItems) {
        this.id = id;
        this.name = mediaName;
        this.description = description;
        if (mediaItems == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = List.copyOf(mediaItems);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<MediaItem> getItems() {
        return items;
    }

    public void addMediaItem(MediaItem item) {
        items.add(item);
    }
}

