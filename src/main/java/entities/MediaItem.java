package entities;

import java.net.URI;

public abstract class MediaItem {
    private final int id;
    private final String title;
    private final String itemDescription;
    private final URI itemLocation;

    // constructor and getters etc...
    public MediaItem(int id, String title, String itemDescription, URI itemLocation){
        this.id = id;
        this.title = title;
        this.itemDescription = itemDescription;
        this.itemLocation = itemLocation;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public URI getItemLocation() {
        return itemLocation;
    }
}

