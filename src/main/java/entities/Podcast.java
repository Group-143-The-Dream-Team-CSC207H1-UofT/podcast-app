package entities;

import java.util.List;

public class Podcast {
    private int id;
    private String podcastName;
    private User createdBy;
    private List<Episode> episodes;

    public Podcast(int id, String podcastName, User createdBy, List<Episode> episodes) {
        this.id = id;
        this.podcastName = podcastName;
        this.createdBy = createdBy;
        this.episodes = episodes;
    }


}

