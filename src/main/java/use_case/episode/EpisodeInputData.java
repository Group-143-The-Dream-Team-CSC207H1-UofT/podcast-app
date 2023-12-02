package use_case.episode;

import entities.TextChunk;
import java.util.UUID;

public class EpisodeInputData {
    final private UUID episodeId;
    final private TextChunk currentTextChunk;

    /**
     * Constructor for creating an instance of EpisodeInputData.
     * Initializes an EpisodeInputData object with the specified episode identifier and the index of the current text chunk.
     *
     * @param episodeId The unique identifier (UUID) of the episode.
     * @param currentTextChunk The current text chunk in the episode.
     */
    public EpisodeInputData(UUID episodeId, TextChunk currentTextChunk) {
        this.episodeId = episodeId;
        this.currentTextChunk = currentTextChunk;
    }

    public UUID getEpisodeId() {
        return episodeId;
    }

    public TextChunk getCurrentTextChunk() {
        return currentTextChunk;
    }
}
