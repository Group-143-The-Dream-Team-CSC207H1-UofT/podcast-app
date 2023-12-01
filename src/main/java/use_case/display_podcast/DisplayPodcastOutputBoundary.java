package use_case.display_podcast;

public interface DisplayPodcastOutputBoundary {
    void prepareSuccessView(DisplayPodcastOutputData outputData);
    void prepareFailView(String error);
}
