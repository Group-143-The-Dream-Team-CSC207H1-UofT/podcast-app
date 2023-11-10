package use_case.upload;

public interface UploadOutputBoundary {
    void prepareSuccessView(UploadOutputData episode);
    void prepareFailView(String error);
}
