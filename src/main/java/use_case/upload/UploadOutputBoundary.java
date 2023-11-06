package use_case.upload;

import use_case.upload.UploadOutputData;

public interface UploadOutputBoundary {

    void PrepareSuccessView(UploadOutputData episode);

    void PrepareFailView(String error);


}
