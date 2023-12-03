package use_case.transcribe;

import entities.Episode;

public class TranscribeInputData {
    final private Episode episode;
    /**
     * Constructor for creating an instance of TranscribeInputData.
     * Initializes a TranscribeInputData object with the provided episode.
     *
     * @param episode The Episode object containing details about the episode to be transcribed.
     */
    public TranscribeInputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }
}
