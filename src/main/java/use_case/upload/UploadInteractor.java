package use_case.upload;

import data_access.MediaItemDataAccess;
import entities.Episode;

public class UploadInteractor implements UploadInputBoundary {
    private final MediaItemDataAccess mediaItemDAO;

    public UploadInteractor(MediaItemDataAccess mediaItemDAO) {
        this.mediaItemDAO = mediaItemDAO;
    }

    public void execute(UploadInputData inputData) {
        if (mediaItemDAO.saveFile(inputData.getAudioFileURI())) {
            // the saving of the file succeeded.

            // here we would have a view. Instead, I'll return for now.
            Episode episode = new Episode(123456789, inputData.getTitle(), inputData.getDescription(),
                    inputData.getAudioFileURI(), null, null);
            // TODO: call the presenter
            // new UploadOutputData(episode, false);
        }
        // the saving of the file failed.
        // TODO: call the presenter
        // new UploadOutputData(null, true);
    }
}
