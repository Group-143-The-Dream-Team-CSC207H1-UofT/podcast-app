package use_case.create_podcast;

import entities.Podcast;
public class CreatePodcastOutputData {

    private final Podcast podcast;

    public CreatePodcastOutputData(Podcast podcast) {
        this.podcast = podcast;
    }

    public Podcast getPodcast() {
        return podcast;
    }
}
