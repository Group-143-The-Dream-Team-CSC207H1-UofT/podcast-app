package use_case.display_podcasts;

public interface DisplayPodcastsInputBoundary {
    /**
     * Gets all podcasts that have been previously saved and passes them to the ouputBoundary
     * @param inputData the input data class in this case does not store anything since nothing is needed
     */
    void execute(DisplayPodcastsInputData inputData);
}
