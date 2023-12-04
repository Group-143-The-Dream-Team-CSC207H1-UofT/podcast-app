package use_case.create_episode;

import entities.Episode;
import entities.Podcast;

public class CreateEpisodeOutputData {
    private final Episode episode;
    private final Podcast podcast;

    public CreateEpisodeOutputData(Episode episode, Podcast podcast) {
        this.podcast = podcast;
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }

    public Podcast getPodcast() {
        return podcast;
    }
}
