package use_case.podcast;
import entities.Episode;
import java.util.ArrayList;


public class PodcastInputData {
    final private String title;
    final private String description;
    final private String author;
    final private ArrayList<Episode> episodes;

    public PodcastInputData(String title, String description, String author, ArrayList<Episode> episodes) {
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
    public ArrayList<Episode> getEpisodes(){
        return episodes;
    }

}
