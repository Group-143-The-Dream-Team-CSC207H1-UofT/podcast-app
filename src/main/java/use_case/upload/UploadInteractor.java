package use_case.upload;

import data_access.MediaItemDataAccess;
import entities.Episode;

import java.util.UUID;

public class UploadInteractor implements UploadInputBoundary {
    private final UploadOutputBoundary outputBoundary;
    private final MediaItemDataAccess mediaItemDAO;

    public UploadInteractor(UploadOutputBoundary outputBoundary, MediaItemDataAccess mediaItemDAO) {
        this.outputBoundary = outputBoundary;
        this.mediaItemDAO = mediaItemDAO;
    }

    public void execute(UploadInputData inputData) {
        UUID uniqueID = UUID.randomUUID();
        if (mediaItemDAO.saveFile(inputData.getAudioFileURI(), uniqueID)) {
            // the saving of the file succeeded.
            Episode episode = new Episode(uniqueID, inputData.getTitle(), inputData.getDescription(),
                    inputData.getAudioFileURI(), null, null);
            outputBoundary.prepareSuccessView(new UploadOutputData(episode, false));
        } else {
            outputBoundary.prepareFailView("Failed to upload file.");
        }
    }
}
