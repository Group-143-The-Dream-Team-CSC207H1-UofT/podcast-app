package use_case.podcast;

import entities.Podcast;
public class PodcastOutputData {

    private final Podcast podcast;

    public PodcastOutputData(Podcast podcast) {
        this.podcast = podcast;
    }

    public Podcast getPodcast() {
        return podcast;
    }
}
