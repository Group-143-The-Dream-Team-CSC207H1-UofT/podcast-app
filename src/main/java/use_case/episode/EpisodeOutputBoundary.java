package use_case.episode;

public interface EpisodeOutputBoundary {
    void prepareSuccessView(EpisodeOutputData episodeOutputData);
    void prepareFailView(String error);
}
