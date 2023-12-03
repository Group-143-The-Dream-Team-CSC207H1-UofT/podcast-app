package interface_adapter.create_episode;

import use_case.create_episode.*;
import java.net.URI;
import java.util.UUID;

public class CreateEpisodeController {
    final CreateEpisodeInputBoundary interactor;

    public CreateEpisodeController(CreateEpisodeInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String title, URI fileLocation, String description, UUID podcastUUID) {
        CreateEpisodeInputData inputData = new CreateEpisodeInputData(title, description, fileLocation, podcastUUID);
        interactor.execute(inputData);
    }
}

