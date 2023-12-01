package interface_adapter.upload;

import use_case.create_episode.*;
import java.net.URI;

public class UploadController {
    final UploadInputBoundary uploadUseCaseInteractor;

    public UploadController(UploadInputBoundary uploadUseCaseInteractor) {
        this.uploadUseCaseInteractor = uploadUseCaseInteractor;
    }

    public void execute(String title, URI fileLocation, String description) {
        UploadInputData uploadInputData = new UploadInputData(
                title, description, fileLocation);

        uploadUseCaseInteractor.execute(uploadInputData);
    }
}

