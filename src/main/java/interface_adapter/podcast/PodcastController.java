package interface_adapter.podcast;

import use_case.podcast.PodcastInputBoundary;
import use_case.podcast.PodcastInputData;

import java.util.UUID;

public class PodcastController {
    final PodcastInputBoundary displayPodcastInteractor;

    public PodcastController(PodcastInputBoundary displayPodcastInteractor) {
        this.displayPodcastInteractor = displayPodcastInteractor;
    }

    /**
     * Passes the podcast ID to the input boundary to display the podcast.
     * @param podcastUUID the ID of the podcast to display.
     */
    public void execute(UUID podcastUUID){
        PodcastInputData podcastInputData = new PodcastInputData(podcastUUID);
        displayPodcastInteractor.execute(podcastInputData);
    }

}
