package use_case.display_podcasts;

public interface DisplayPodcastsOutputBoundary {
    void prepareSuccessView(DisplayPodcastsOutputData outputData);
    void prepareFailView(DisplayPodcastsOutputData outputData);
}
