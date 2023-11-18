package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.Podcast;
import use_case.podcast.PodcastOutputData;

import java.util.List;

public class DisplayPodcastsInteractor {
    private final DisplayPodcastsOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;
    public DisplayPodcastsInteractor(DisplayPodcastsOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }
    public void execute() {
        List<Podcast> allPodcasts = podcastDAO.getAllPodcasts();
        DisplayPodcastsOutputData outputData = new DisplayPodcastsOutputData(allPodcasts, false);
        outputBoundary.prepareSuccessView(outputData);
    }
}
