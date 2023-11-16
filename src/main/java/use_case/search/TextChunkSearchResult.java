package use_case.search;

import entities.Episode;
import entities.TextChunk;

public class TextChunkSearchResult extends SearchResult {
    private final Episode episode;
    private final TextChunk textChunk;

    public TextChunkSearchResult(Episode episode, TextChunk textChunk) {
        this.episode = episode;
        this.textChunk = textChunk;
    }

    public Episode getEpisode() {
        return episode;
    }

    public TextChunk getTextChunk() {
        return textChunk;
    }
}
