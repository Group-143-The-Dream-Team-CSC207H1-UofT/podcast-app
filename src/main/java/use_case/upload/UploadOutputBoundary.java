package use_case.upload;

import use_case.upload.UploadOutputData;

public interface UploadOutputBoundary {

    void PrepareSuccessView(UploadOutputData episode);

    void PrepareFailView(String error);


    // That is all that we need for UploadOutputBoundary, however this interface
    // will change whether we decide to have two processes: Upload and Transcribe
    // or simply Transcribe. In which case the following is need:

//    void PrepareUploadSuccessView(UploadOutputData episode);
//
//    void PrepareUploadFailView(String error);

//    void PrepareTranscribeSuccessView(UploadOutputData episode);
//
//    void PrepareTranscribeFailView(String error);

}
