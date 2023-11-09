package entities;

import java.util.List;

public abstract class MediaCollection {
    private final int id;
    private final String name;
    private List<MediaItem> items;

    // Constructors, getters ...
    public MediaCollection(int id, String mediaName, List<MediaItem> mediaItems) {
        this.id = id;
        this.name = mediaName;
        this.items = List.copyOf(mediaItems);
    }

    public int getId() {
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

