package use_case.transcribe;

import entities.Episode;

public class TranscribeOutputData {
    private final Episode episode;
    private final boolean useCaseFailed;
    public TranscribeOutputData(Episode episode, boolean useCaseFailed) {
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
