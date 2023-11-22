package use_case.display_episode;

public interface DisplayEpisodeOutputBoundary {
    void prepareSuccessView(DisplayEpisodeOutputData displayEpisodeOutputData);
    void prepareFailView(String error);
}
