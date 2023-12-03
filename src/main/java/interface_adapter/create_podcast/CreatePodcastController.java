package interface_adapter.create_podcast;

import use_case.create_podcast.CreatePodcastInputBoundary;
import use_case.create_podcast.CreatePodcastInputData;

public class CreatePodcastController {
    private final CreatePodcastInputBoundary inputBoundary;
    public CreatePodcastController(CreatePodcastInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(String title, String description) {
        CreatePodcastInputData inputData = new CreatePodcastInputData(title, description);
        inputBoundary.execute(inputData);
    }
}
