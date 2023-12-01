package interface_adapter.display_podcast;

import entities.MediaItem;
import entities.User;
import use_case.create_podcast.CreatePodcastInputBoundary;
import use_case.create_podcast.CreatePodcastInputData;

import java.util.List;

public class DisplayPodcastController {

    final CreatePodcastInputBoundary podcastInteractor;

    public DisplayPodcastController(CreatePodcastInputBoundary podcastInteractor) {
        this.podcastInteractor = podcastInteractor;
    }

    public void execute(String title, String description, User assignedTo, List<MediaItem> episodes){
        CreatePodcastInputData createPodcastInputData = new CreatePodcastInputData(title, description,
                assignedTo, episodes);

        podcastInteractor.execute(createPodcastInputData);

    }

}
