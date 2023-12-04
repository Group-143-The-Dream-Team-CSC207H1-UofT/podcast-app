package interface_adapter.create_podcast;

import use_case.create_podcast.CreatePodcastInputBoundary;
import use_case.create_podcast.CreatePodcastInputData;

public class CreatePodcastController {
    private final CreatePodcastInputBoundary inputBoundary;
    public CreatePodcastController(CreatePodcastInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Takes in a title and description and calls the input boundary to create a new podcast.
     * @param title The title of the new podcast
     * @param description The description of the new podcast
     */
    public void execute(String title, String description) {
        CreatePodcastInputData inputData = new CreatePodcastInputData(title, description);
        inputBoundary.execute(inputData);
    }
}
