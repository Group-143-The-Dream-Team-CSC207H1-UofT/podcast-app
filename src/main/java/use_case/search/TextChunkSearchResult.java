package use_case.search;

import entities.Episode;
import entities.TextChunk;

public class TextChunkSearchResult extends SearchResult {
    private final Episode episode;
    private final TextChunk textChunk;

    /**
     * Constructor for the TextChunkSearchResult.
     * @param episode
     * @param textChunk
     */
    public TextChunkSearchResult(Episode episode, TextChunk textChunk) {
        this.episode = episode;
        this.textChunk = textChunk;
    }

    /**
     * @return the episode contained in this object.
     */
    public Episode getEpisode() {
        return episode;
    }

    /**
     * @return the TextChunk contained in this object.
     */
    public TextChunk getTextChunk() {
        return textChunk;
    }
}
