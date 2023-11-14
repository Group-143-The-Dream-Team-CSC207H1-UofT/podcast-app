package interface_adapter.search_index;

import entities.Episode;

public class SearchIndexState {
    private Episode episode;
    private String errorMessage;

    public SearchIndexState() {}

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return this.episode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
