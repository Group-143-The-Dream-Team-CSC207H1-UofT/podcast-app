package interface_adapter.create_episode;

import use_case.create_episode.*;
import java.net.URI;
import java.util.UUID;

public class CreateEpisodeController {
    final CreateEpisodeInputBoundary interactor;

    public CreateEpisodeController(CreateEpisodeInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the CreateEpisodeInputBoundary to create a new episode of a podcast.
     * @param title The title of the new episode.
     * @param fileLocation The location of the audio file associated with the episode.
     * @param description The description of the episode.
     * @param podcastUUID The unique ID of the episode.
     */
    public void execute(String title, URI fileLocation, String description, UUID podcastUUID) {
        CreateEpisodeInputData inputData = new CreateEpisodeInputData(title, description, fileLocation, podcastUUID);
        interactor.execute(inputData);
    }
}

