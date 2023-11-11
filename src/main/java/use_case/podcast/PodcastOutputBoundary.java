package use_case.podcast;

public interface PodcastOutputBoundary {

    void prepareSuccessView(PodcastOutputData podcast);
    void prepareFailView(String error);

}
