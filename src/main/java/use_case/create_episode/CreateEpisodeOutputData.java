package use_case.create_episode;

import entities.Episode;

public class CreateEpisodeOutputData {
    private final Episode episode;

    public CreateEpisodeOutputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }
}
