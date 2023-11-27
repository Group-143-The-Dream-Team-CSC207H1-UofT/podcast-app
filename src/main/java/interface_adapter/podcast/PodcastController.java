package interface_adapter.podcast;

import entities.MediaItem;
import entities.User;
import use_case.podcast.PodcastInputBoundary;
import use_case.podcast.PodcastInputData;

import java.util.List;

public class PodcastController {

    final PodcastInputBoundary podcastInteractor;

    public PodcastController(PodcastInputBoundary podcastInteractor) {
        this.podcastInteractor = podcastInteractor;
    }

    public void execute(String title, String description, User assignedTo, List<MediaItem> episodes){
        PodcastInputData podcastInputData = new PodcastInputData(title, description,
                assignedTo, episodes);

        podcastInteractor.execute(podcastInputData);

    }

}
