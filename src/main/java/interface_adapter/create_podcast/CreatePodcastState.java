package interface_adapter.create_podcast;

import entities.Podcast;

public class CreatePodcastState {
    private String title = "";
    private String description = "";
    private String error = null;
    public CreatePodcastState() {};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
