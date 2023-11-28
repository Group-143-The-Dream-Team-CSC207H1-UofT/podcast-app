package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.Podcast;
import use_case.podcast.PodcastOutputData;

import java.util.ArrayList;
import java.util.List;

public class DisplayPodcastsInteractor implements DisplayPodcastsInputBoundary{
    private final DisplayPodcastsOutputBoundary outputBoundary;
    private final PodcastDataAccess podcastDAO;

    /**
     * Creates a new instance of the DisplayPodcastsInteractor, which will load all saved podcasts and pass them to
     * the presenter
     * @param outputBoundary the presenter that will display the podcasts
     * @param podcastDAO the data access object that can load all podcasts from the data storage
     */
    public DisplayPodcastsInteractor(DisplayPodcastsOutputBoundary outputBoundary, PodcastDataAccess podcastDAO) {
        this.outputBoundary = outputBoundary;
        this.podcastDAO = podcastDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DisplayPodcastsInputData inputData) {
        List<Podcast> allPodcasts = new ArrayList<>(podcastDAO.getAllPodcasts());
        DisplayPodcastsOutputData outputData = new DisplayPodcastsOutputData(allPodcasts, null, false);
        outputBoundary.prepareSuccessView(outputData);
    }
}
