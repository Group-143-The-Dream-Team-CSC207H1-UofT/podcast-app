package use_case.upload;

import data_access.MediaItemDataAccess;
import entities.Episode;

public class UploadInteractor {
    private final MediaItemDataAccess mediaItemDAO;

    public UploadInteractor(MediaItemDataAccess mediaItemDAO) {
        this.mediaItemDAO = mediaItemDAO;
    }

    public UploadOutputData execute(UploadInputData inputData) {
        if (mediaItemDAO.saveFile(inputData.getAudioFilePath())) {
            // the saving of the file succeeded.

            // here we would have a view. Instead, I'll return for now.
            Episode episode = new Episode(123456789, inputData.getTitle(), inputData.getDescription(),
                    inputData.getAudioFilePath(), null, null);
            return new UploadOutputData(episode, false);
        }
        // the saving of the file failed.
        return new UploadOutputData(null, true);
    }
}
