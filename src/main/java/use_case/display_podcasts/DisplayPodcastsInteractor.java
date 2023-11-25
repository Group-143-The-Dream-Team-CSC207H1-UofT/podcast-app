package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.Podcast;
import use_case.podcast.PodcastOutputData;

import java.util.ArrayList;
import java.util.List;

public class DisplayPodcastsInteractor implements DisplayPodcastsInputBoundary{
    private final DisplayPodcastsOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;
    public DisplayPodcastsInteractor(DisplayPodcastsOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }

    @Override
    public void execute(DisplayPodcastsInputData inputData) {
        List<Podcast> allPodcasts = new ArrayList<>(podcastDAO.getAllPodcasts());
        DisplayPodcastsOutputData outputData = new DisplayPodcastsOutputData(allPodcasts, null, false);
        outputBoundary.prepareSuccessView(outputData);
    }
}
