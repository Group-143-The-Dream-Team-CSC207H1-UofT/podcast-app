package interface_adapter.transcribe;

import entities.Episode;
import use_case.transcribe.TranscribeInputBoundary;
import use_case.transcribe.TranscribeInputData;

public class TranscribeController {
    final TranscribeInputBoundary transcribeUseCaseInteractor;

    public TranscribeController(TranscribeInputBoundary transcribeUseCaseInteractor) {
        this.transcribeUseCaseInteractor = transcribeUseCaseInteractor;
    }
    public void execute(Episode episode) {
        TranscribeInputData transcribeInputData = new TranscribeInputData(episode);
        transcribeUseCaseInteractor.execute(transcribeInputData);
    }
}
