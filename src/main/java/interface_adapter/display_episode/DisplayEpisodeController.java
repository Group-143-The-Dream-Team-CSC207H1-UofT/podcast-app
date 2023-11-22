package interface_adapter.display_episode;

import use_case.display_episode.DisplayEpisodeInputBoundary;
import use_case.display_episode.DisplayEpisodeInputData;

import java.util.UUID;

public class DisplayEpisodeController {

    final DisplayEpisodeInputBoundary displayEpisodeInteractor;

    public DisplayEpisodeController(DisplayEpisodeInputBoundary displayEpisodeInteractor){
        this.displayEpisodeInteractor = displayEpisodeInteractor;
    }

    public void execute(UUID episodeUUID){
        DisplayEpisodeInputData displayEpisodeInputData = new DisplayEpisodeInputData(episodeUUID);
        displayEpisodeInteractor.execute(displayEpisodeInputData);
    }
}
