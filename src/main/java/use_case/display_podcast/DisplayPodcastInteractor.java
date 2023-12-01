package use_case.display_podcast;


import data_access.PodcastDataAccess;
import entities.Podcast;

import java.util.UUID;

public class DisplayPodcastInteractor implements DisplayPodcastInputBoundary {
    private final DisplayPodcastOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;
    public DisplayPodcastInteractor(DisplayPodcastOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }
    @Override
    public void execute(DisplayPodcastInputData displayPodcastInputData) {
        UUID podcastUUID = displayPodcastInputData.getPodcastID();
        Podcast podcast = podcastDAO.getPodcastById(podcastUUID);
        if (podcast == null) {
            outputBoundary.prepareFailView("Podcast with ID " + podcastUUID + " does not exist");
        } else {
            DisplayPodcastOutputData displayPodcastsOutputData = new DisplayPodcastOutputData(podcast, false);
            outputBoundary.prepareSuccessView(displayPodcastsOutputData);
        }

    }
}
