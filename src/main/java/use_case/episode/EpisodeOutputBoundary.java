package use_case.episode;

public interface EpisodeOutputBoundary {
    /**
     * Prepares the view for successfuly displaying an episode.
     *
     * @param episodeOutputData The output data from the display episode use case.
     */
    void prepareSuccessView(EpisodeOutputData episodeOutputData);
    /**
     * Prepares the view for a fail in displaying episode.
     *
     * @param error The error message resulting from a failed operation.
     */
    void prepareFailView(String error);
}
