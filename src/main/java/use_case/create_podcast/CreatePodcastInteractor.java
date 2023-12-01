package use_case.create_podcast;


import data_access.PodcastDataAccess;
import entities.Podcast;
import java.util.UUID;

public class CreatePodcastInteractor implements CreatePodcastInputBoundary {

    private final CreatePodcastOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;

    public CreatePodcastInteractor(CreatePodcastOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }

    @Override
    public void execute(CreatePodcastInputData createPodcastInputData) {
        UUID uniqueID = UUID.randomUUID();
        Podcast podcast = new Podcast(uniqueID, createPodcastInputData.getTitle(), createPodcastInputData.getDescription(), null, null);
        if (podcastDAO.savePodcast(podcast)){
            outputBoundary.prepareSuccessView(new CreatePodcastOutputData(podcast));
        } else {
            outputBoundary.prepareFailView("Failed to save podcast.");
        }
    }
}
