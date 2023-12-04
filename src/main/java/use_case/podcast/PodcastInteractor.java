package use_case.podcast;


import data_access.PodcastDataAccess;
import entities.Podcast;

import java.util.UUID;

public class PodcastInteractor implements PodcastInputBoundary {
    private final PodcastOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;
    public PodcastInteractor(PodcastOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }

    /**
     * Fetches the desired podcast and tells the output boundary to display it.
     * @param podcastInputData contains the ID of the podcast to display.
     */
    @Override
    public void execute(PodcastInputData podcastInputData) {
        UUID podcastUUID = podcastInputData.getPodcastID();
        Podcast podcast = podcastDAO.getPodcastById(podcastUUID);
        if (podcast == null) {
            outputBoundary.prepareFailView("Podcast with ID " + podcastUUID + " does not exist");
        } else {
            PodcastOutputData displayPodcastsOutputData = new PodcastOutputData(podcast, false);
            outputBoundary.prepareSuccessView(displayPodcastsOutputData);
        }

    }
}
