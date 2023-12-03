package use_case.podcast;

public interface PodcastOutputBoundary {
    void prepareSuccessView(PodcastOutputData outputData);
    void prepareFailView(String error);
}
