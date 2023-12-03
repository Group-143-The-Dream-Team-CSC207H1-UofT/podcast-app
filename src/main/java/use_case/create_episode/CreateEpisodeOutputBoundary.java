package use_case.create_episode;

public interface CreateEpisodeOutputBoundary {
    void prepareSuccessView(CreateEpisodeOutputData outputData);
    void prepareFailView(String error);
}
