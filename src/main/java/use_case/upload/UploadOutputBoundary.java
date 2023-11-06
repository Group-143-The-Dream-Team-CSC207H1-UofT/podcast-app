package use_case.upload;

import use_case.upload.UploadOutputData;

public interface UploadOutputBoundary {
    void prepareSuccessView(UploadOutputData episode);

    void prepareFailView(String error);

}
