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

    @Override
    public void execute(PodcastInputData podcastInputData) {
        UUID uniqueID = UUID.randomUUID();
        Podcast podcast = new Podcast(uniqueID, podcastInputData.getTitle(), podcastInputData.getAssignedTo(), podcastInputData.getEpisodes());
        if (podcastDAO.savePodcast(podcast)){
            outputBoundary.prepareSuccessView(new PodcastOutputData(podcast));
        } else {
            outputBoundary.prepareFailView("Failed to save podcast.");
        }
    }
}
