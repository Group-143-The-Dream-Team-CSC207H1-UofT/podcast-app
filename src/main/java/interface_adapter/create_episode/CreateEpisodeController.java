package interface_adapter.create_episode;

import use_case.create_episode.*;
import java.net.URI;

public class CreateEpisodeController {
    final CreateEpisodeInputBoundary interactor;

    public CreateEpisodeController(CreateEpisodeInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String title, URI fileLocation, String description) {
        CreateEpisodeInputData inputData = new CreateEpisodeInputData(title, description, fileLocation);
        interactor.execute(inputData);
    }
}

