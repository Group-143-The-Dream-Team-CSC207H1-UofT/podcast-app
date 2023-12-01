package use_case.create_podcast;

public interface CreatePodcastOutputBoundary {

    void prepareSuccessView(CreatePodcastOutputData podcast);
    void prepareFailView(String error);

}
