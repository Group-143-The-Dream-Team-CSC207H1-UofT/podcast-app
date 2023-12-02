package use_case.transcribe;


public interface TranscribeOutputBoundary {

    /**
     * Prepares the view for a successful transcription.
     * This method is called when the transcription process completes successfully. It receives
     * the transcription output data, which can then be used to update the view accordingly.
     *
     * @param transcribeOutputData The data resulting from a successful transcription process.
     */
    void prepareSuccessView(TranscribeOutputData transcribeOutputData);

    /**
     * Prepares the view for a failed transcription.
     * This method is called when the transcription process fails. It receives an error message
     * which can be used to notify the user of the failure or to update the view with error information.
     *
     * @param error The error message resulting from a failed transcription process.
     */
    void prepareFailView(String error);
}
