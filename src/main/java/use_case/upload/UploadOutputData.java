package use_case.upload;

import entities.Episode;

public class UploadOutputData {
    private final boolean useCaseFailed;
    public UploadOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}
