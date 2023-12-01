package interface_adapter.create_episode;

import entities.Episode;

public class CreateEpisodeState {
    private Episode episode = null;
    private String errorMessage = "";
    public CreateEpisodeState() {}

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
