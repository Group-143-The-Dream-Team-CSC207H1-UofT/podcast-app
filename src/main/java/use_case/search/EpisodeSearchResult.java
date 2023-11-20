package use_case.search;

import entities.Episode;

public class EpisodeSearchResult extends SearchResult {
    private final Episode episode;

    public EpisodeSearchResult(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }
}
