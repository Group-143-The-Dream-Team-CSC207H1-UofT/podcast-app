package use_case.upload;

import entities.Episode;

public class UploadOutputData {
    private final Episode episode;
    private final boolean useCaseFailed;
    public UploadOutputData(Episode episode, boolean useCaseFailed) {
        this.episode = episode;
        this.useCaseFailed = useCaseFailed;
    }

    public Episode getEpisode() {
        return episode;
    }

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}
