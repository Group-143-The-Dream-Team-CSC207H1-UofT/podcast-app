package interface_adapter.search_index;

import entities.Episode;

public class SearchIndexState {
    private final Episode episode;
    private final String errorMessage;

    public SearchIndexState(Episode episode, String errorMessage) {
        this.episode = episode;
        this.errorMessage = errorMessage;
    }

    public Episode getEpisode() {
        return this.episode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
