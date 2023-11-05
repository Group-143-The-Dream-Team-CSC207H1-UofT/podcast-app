package interface_adapter.upload;


import use_case.upload.*;

public class UploadController {
    final UploadInputBoundary uploadUseCaseInteractor;

    public UploadController(UploadInputBoundary uploadUseCaseInteractor) {
        this.uploadUseCaseInteractor = uploadUseCaseInteractor;
    }
    // why do we need description as input data?
    public void execute(String title, String filePath, String description) {
        UploadInputData uploadInputData = new UploadInputData(
                title, filePath, description);

        uploadUseCaseInteractor.execute(uploadInputData);
    }
}

