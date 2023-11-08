package entities;

import java.net.URI;
import java.util.UUID;

public abstract class MediaItem {
    private final UUID id;
    private final String title;
    private final String itemDescription;

    // constructor and getters etc...
    public MediaItem(UUID id, String title, String itemDescription){
        this.id = id;
        this.title = title;
        this.itemDescription = itemDescription;
    }


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}

