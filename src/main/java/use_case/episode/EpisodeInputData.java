package use_case.episode;

import java.util.UUID;

public class EpisodeInputData {
    final private UUID episodeId;
    final private int currentTextChunkIndex;
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
