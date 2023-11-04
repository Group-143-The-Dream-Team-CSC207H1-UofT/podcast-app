package entities;

public abstract class MediaItem {
    private final int id;
    private final String title;
    private final String itemDescription;
    private final String itemLocation;

    // constructor and getters etc...
    public MediaItem(int id, String title, String itemDescription, String itemLocation){
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

    public String getItemLocation() {
        return itemLocation;
    }
}

