package use_case.create_podcast;

import entities.MediaItem;
import entities.User;

import java.util.List;


public class CreatePodcastInputData {
    final private String title;
    final private String description;

    public CreatePodcastInputData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }



}
