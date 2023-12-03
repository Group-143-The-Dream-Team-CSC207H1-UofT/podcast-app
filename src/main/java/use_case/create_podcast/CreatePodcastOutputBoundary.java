package use_case.create_podcast;

public interface CreatePodcastOutputBoundary {

    void prepareSuccessView(CreatePodcastOutputData outputData);
    void prepareFailView(String error);

}
