package entities;

public abstract class MediaItem {
    private final int id;
    private final String title;
    private final String itemDescription;

    // constructor and getters etc...
    public MediaItem(int id, String title, String itemDescription){
    this.id = id;
    this.title = title;
    this.itemDescription = itemDescription;
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
}

