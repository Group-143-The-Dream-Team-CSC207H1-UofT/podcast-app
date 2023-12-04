package interface_adapter.home;

import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeController {
    private final HomeInputBoundary displayPodcastsInteractor;
    public HomeController(HomeInputBoundary displayPodcastsInteractor) {
        this.displayPodcastsInteractor = displayPodcastsInteractor;
    }

    /**
     * Calls the input boundary to retrieve all podcasts.
     */
    public void execute() {
        HomeInputData inputData = new HomeInputData();
        // The input data here contains nothing
        // In future, it might contain a User so I will keep it for now
        displayPodcastsInteractor.execute(inputData);
    }

}
