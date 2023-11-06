package use_case.upload;

import data_access.MediaItemDataAccess;
import entities.Episode;

import java.util.UUID;

public class UploadInteractor implements UploadInputBoundary {
    private final MediaItemDataAccess mediaItemDAO;

    public UploadInteractor(MediaItemDataAccess mediaItemDAO) {
        this.mediaItemDAO = mediaItemDAO;
    }

    public void execute(UploadInputData inputData) {
        UUID uniqueID = UUID.randomUUID();
        if (mediaItemDAO.saveFile(inputData.getAudioFileURI(), uniqueID)) {
            // the saving of the file succeeded.

            // here we would have a view. Instead, I'll return for now.
            Episode episode = new Episode(uniqueID, inputData.getTitle(), inputData.getDescription(),
                    inputData.getAudioFileURI(), null, null);
            // TODO: call the presenter
            // new UploadOutputData(episode, false);
        }
        // the saving of the file failed.
        // TODO: call the presenter
        // new UploadOutputData(null, true);
    }
}
