package use_case.transcribe;


public interface TranscribeOutputBoundary {
    void prepareSuccessView(TranscribeOutputData transcribeOutputData);
    void prepareFailView(String error);
}
