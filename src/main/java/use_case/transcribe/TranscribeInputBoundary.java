package use_case.transcribe;

import java.io.IOException;

public interface TranscribeInputBoundary {

    /**
     * Executes the transcription process with the given input data.
     * This method is responsible for initiating the transcription operation. It accepts
     * an instance of TranscribeInputData which contains the data necessary for
     * the transcription process.
     *
     * @param inputdata The input data required for the transcription process.
     */
    void execute(TranscribeInputData inputdata);
}
