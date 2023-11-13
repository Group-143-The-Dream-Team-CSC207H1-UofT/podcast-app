package use_case.transcribe;

import entities.Episode;

public class TranscribeInputData {
    final private Episode episode;

    public TranscribeInputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }
}
