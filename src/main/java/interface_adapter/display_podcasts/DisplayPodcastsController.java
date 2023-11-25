package interface_adapter.display_podcasts;

import use_case.display_podcasts.DisplayPodcastsInputBoundary;
import use_case.display_podcasts.DisplayPodcastsInputData;

public class DisplayPodcastsController {
    private final DisplayPodcastsInputBoundary displayPodcastsInteractor;
    public DisplayPodcastsController(DisplayPodcastsInputBoundary displayPodcastsInteractor) {
        this.displayPodcastsInteractor = displayPodcastsInteractor;
    }

    public void execute() {
        DisplayPodcastsInputData inputData = new DisplayPodcastsInputData();
        // The input data here contains nothing
        // In future, it might contain a User so I will keep it for now
        displayPodcastsInteractor.execute(inputData);
    }

}
