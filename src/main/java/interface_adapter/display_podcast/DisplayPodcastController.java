package interface_adapter.display_podcast;

import entities.MediaItem;
import entities.User;
import use_case.create_podcast.CreatePodcastInputBoundary;
import use_case.create_podcast.CreatePodcastInputData;
import use_case.display_podcast.DisplayPodcastInputBoundary;
import use_case.display_podcast.DisplayPodcastInputData;

import java.util.List;
import java.util.UUID;

public class DisplayPodcastController {

    final DisplayPodcastInputBoundary displayPodcastInteractor;

    public DisplayPodcastController(DisplayPodcastInputBoundary displayPodcastInteractor) {
        this.displayPodcastInteractor = displayPodcastInteractor;
    }

    public void execute(UUID podcastUUID){
        DisplayPodcastInputData displayPodcastInputData = new DisplayPodcastInputData(podcastUUID);

        displayPodcastInteractor.execute(displayPodcastInputData);

    }

}
