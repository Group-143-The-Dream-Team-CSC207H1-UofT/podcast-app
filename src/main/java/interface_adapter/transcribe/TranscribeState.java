package interface_adapter.transcribe;

import entities.Episode;

public class TranscribeState {
    private Episode episode = null;
    private String errorMessage = "";

    public TranscribeState() {}


    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}