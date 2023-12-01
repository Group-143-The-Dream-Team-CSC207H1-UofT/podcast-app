package use_case.create_episode;

public interface UploadOutputBoundary {
    void prepareSuccessView(UploadOutputData episode);
    void prepareFailView(String error);
}
