package interface_adapter.podcast;

import use_case.podcast.PodcastInputBoundary;
import use_case.podcast.PodcastInputData;

import java.util.UUID;

public class PodcastController {

    final PodcastInputBoundary displayPodcastInteractor;

    public PodcastController(PodcastInputBoundary displayPodcastInteractor) {
        this.displayPodcastInteractor = displayPodcastInteractor;
    }

    public void execute(UUID podcastUUID){
        PodcastInputData podcastInputData = new PodcastInputData(podcastUUID);

        displayPodcastInteractor.execute(podcastInputData);

    }

}
