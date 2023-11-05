package interface_adapter.upload;


import use_case.upload.*;

public class UploadController {
    final UploadInputBoundary uploadUseCaseInteractor;

    public UploadController(UploadInputBoundary uploadUseCaseInteractor) {
        this.uploadUseCaseInteractor = uploadUseCaseInteractor;
    }

    public void execute(String title, String filePath) {
        UploadInputData signupInputData = new UploadInputData(
                title, filePath);

        uploadUseCaseInteractor.execute(signupInputData);
    }
}

