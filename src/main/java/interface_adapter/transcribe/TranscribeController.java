package interface_adapter.transcribe;

import entities.Episode;
import use_case.transcribe.TranscribeInputBoundary;
import use_case.transcribe.TranscribeInputData;

public class TranscribeController {
    final TranscribeInputBoundary transcribeUseCaseInteractor;

    public TranscribeController(TranscribeInputBoundary transcribeUseCaseInteractor) {
        this.transcribeUseCaseInteractor = transcribeUseCaseInteractor;
    }

    /**
     * Initiates the transcription process for the given episode.
     * This method creates a TranscribeInputDataobject from the provided episode
     * and passes it to the transcription use case interactor for processing.
     *
     * @param episode The episode to be transcribed.
     */
    public void execute(Episode episode) {
        TranscribeInputData transcribeInputData = new TranscribeInputData(episode);
        transcribeUseCaseInteractor.execute(transcribeInputData);
    }
}
