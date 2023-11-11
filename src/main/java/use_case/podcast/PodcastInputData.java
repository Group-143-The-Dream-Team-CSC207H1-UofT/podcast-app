package use_case.podcast;

import entities.MediaItem;
import java.util.List;


public class PodcastInputData {
    final private String title;
    final private String description;
    final private String author;
    final private List<MediaItem> episodes;

    // Todo: Determine whether we need a genre instance attribute

    public PodcastInputData(String title, String description, String author, List<MediaItem> episodes) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public List<MediaItem> getEpisodes(){
        return episodes;
    }


}
