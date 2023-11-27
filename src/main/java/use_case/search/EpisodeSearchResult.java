package use_case.search;

import entities.Episode;

public class EpisodeSearchResult extends SearchResult {
    private final Episode episode;

    /**
     * Constructor for EpisodeSearchResult.
     * @param episode
     */
    public EpisodeSearchResult(Episode episode) {
        this.episode = episode;
    }

    /**
     * @return the episode contained in this search result.
     */
    public Episode getEpisode() {
        return episode;
    }
}
