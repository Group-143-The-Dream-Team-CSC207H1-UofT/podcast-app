package interface_adapter.upload;

import entities.Episode;

public class UploadState {
    private Episode episode = null;
    private String errorMessage = "";

    public UploadState() {}


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
