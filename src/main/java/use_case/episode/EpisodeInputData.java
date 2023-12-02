package use_case.episode;

import java.util.UUID;

public class EpisodeInputData {
    final private UUID episodeId;
    final private int currentTextChunkIndex;

    /**
     * Constructor for creating an instance of EpisodeInputData.
     * Initializes an EpisodeInputData object with the specified episode identifier and the index of the current text chunk.
     *
     * @param episodeId The unique identifier (UUID) of the episode.
     * @param currentTextChunkIndex The index of the current text chunk in the episode.
     */
    public EpisodeInputData(UUID episodeId, int currentTextChunkIndex) {
        this.episodeId = episodeId;
        this.currentTextChunkIndex = currentTextChunkIndex;
    }

    public UUID getEpisodeId() {
        return episodeId;
    }

    public int getCurrentTextChunkIndex() {
        return currentTextChunkIndex;
    }
}
