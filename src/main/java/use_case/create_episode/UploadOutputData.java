package use_case.create_episode;

import entities.Episode;

public class UploadOutputData {
    private final Episode episode;

    public UploadOutputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }
}
