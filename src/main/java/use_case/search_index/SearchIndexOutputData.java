package use_case.search_index;

import entities.Episode;

public class SearchIndexOutputData {
    private final Episode episode;

    public SearchIndexOutputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }
}
