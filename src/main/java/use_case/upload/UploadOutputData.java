package use_case.upload;

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
