package interface_adapter.episode;

import entities.TextChunk;
import use_case.episode.EpisodeInputBoundary;
import use_case.episode.EpisodeInputData;

import java.util.UUID;

public class EpisodeController {

    final EpisodeInputBoundary displayEpisodeInteractor;

    public EpisodeController(EpisodeInputBoundary displayEpisodeInteractor){
        this.displayEpisodeInteractor = displayEpisodeInteractor;
    }

    /**
     * Requires: episode is saved and valid
     * Requires: text chunk index is valid for the episode corresponding to this episode ID.
     * @param episodeUUID ID of the episode to be displayed
     * @param currentTextChunk The current text chunk from the episode to be highlighted in Episode View
     */
    public void execute(UUID episodeUUID, TextChunk currentTextChunk){
        EpisodeInputData episodeInputData = new EpisodeInputData(episodeUUID, currentTextChunk);
        displayEpisodeInteractor.execute(episodeInputData);
    }
}
