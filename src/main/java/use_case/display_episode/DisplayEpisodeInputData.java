package use_case.display_episode;

import java.util.UUID;

public class DisplayEpisodeInputData {
    final private UUID episodeId;
    final private int currentTextChunkIndex;
    public DisplayEpisodeInputData(UUID episodeId, int currentTextChunkIndex) {
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
