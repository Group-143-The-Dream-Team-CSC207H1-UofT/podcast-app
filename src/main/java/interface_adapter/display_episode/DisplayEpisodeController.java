package interface_adapter.display_episode;

import use_case.display_episode.DisplayEpisodeInputBoundary;
import use_case.display_episode.DisplayEpisodeInputData;

import java.util.UUID;

public class DisplayEpisodeController {

    final DisplayEpisodeInputBoundary displayEpisodeInteractor;

    public DisplayEpisodeController(DisplayEpisodeInputBoundary displayEpisodeInteractor){
        this.displayEpisodeInteractor = displayEpisodeInteractor;
    }

    /**
     * Requires: episode is saved and valid
     * @param episodeUUID ID of the episode to be displayed
     */
    public void execute(UUID episodeUUID){
        DisplayEpisodeInputData displayEpisodeInputData = new DisplayEpisodeInputData(episodeUUID, 0);
        displayEpisodeInteractor.execute(displayEpisodeInputData);
    }
    /**
     * * Requires: episode is saved and valid
     * Requires: text chunk index is valid for the episode corresponding to this episode ID.
     * @param episodeUUID ID of the episode to be displayed
     * @param currentTextChunkIndex Index of the current text chunk from the episode to be highlighted in Episode View
     */
    public void execute(UUID episodeUUID, int currentTextChunkIndex){
        DisplayEpisodeInputData displayEpisodeInputData = new DisplayEpisodeInputData(episodeUUID, currentTextChunkIndex);
        displayEpisodeInteractor.execute(displayEpisodeInputData);
    }
}
