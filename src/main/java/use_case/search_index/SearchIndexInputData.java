package use_case.search_index;

import entities.Episode;

public class SearchIndexInputData {
    private final Episode episode;

    public SearchIndexInputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return this.episode;
    }
}
