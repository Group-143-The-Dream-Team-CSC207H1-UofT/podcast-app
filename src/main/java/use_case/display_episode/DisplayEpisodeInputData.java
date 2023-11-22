package use_case.display_episode;

import java.util.UUID;

public class DisplayEpisodeInputData {
    final private UUID episodeId;

    public DisplayEpisodeInputData(UUID episodeId) {
        this.episodeId = episodeId;
    }

    public UUID getEpisodeId() {
        return episodeId;
    }

}
