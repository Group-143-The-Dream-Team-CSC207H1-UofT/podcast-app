package use_case.episode;

public interface EpisodeInputBoundary {

    /**
     * Executes the processs of displaying an episode based on the input data.
     * This method accepts EpisodeInputData which contains necessary information
     * about an episode. Implementations will process this data according to the specific
     * business rules and use cases of the application.
     *
     * @param episodeInputData The input data for displaying an episode.
     */
    void execute(EpisodeInputData episodeInputData);
}
