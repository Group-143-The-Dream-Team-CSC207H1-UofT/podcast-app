package use_case.home;

import data_access.PodcastDataAccess;
import entities.Podcast;

import java.util.ArrayList;
import java.util.List;

public class HomeInteractor implements HomeInputBoundary {
    private final HomeOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;
    public HomeInteractor(HomeOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }

    /**
     * Fetches all saved podcasts and tells output boundary to display them.
     * @param inputData Contains nothing.
     */
    @Override
    public void execute(HomeInputData inputData) {
        List<Podcast> allPodcasts = new ArrayList<>(podcastDAO.getAllPodcasts());
        HomeOutputData outputData = new HomeOutputData(allPodcasts, null, false);
        outputBoundary.prepareSuccessView(outputData);
    }
}
