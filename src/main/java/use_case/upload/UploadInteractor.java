package use_case.upload;

import data_access.EpisodeDataAccess;
import entities.Episode;

import java.util.UUID;

public class UploadInteractor implements UploadInputBoundary {
    private final UploadOutputBoundary outputBoundary;
    private final EpisodeDataAccess episodeDAO;

    public UploadInteractor(UploadOutputBoundary outputBoundary, EpisodeDataAccess episodeDAO) {
        this.outputBoundary = outputBoundary;
        this.episodeDAO = episodeDAO;
    }

    public void execute(UploadInputData inputData) {
        UUID uniqueID = UUID.randomUUID();
        if (episodeDAO.saveFile(inputData.getAudioFileURI(), uniqueID)) {
            // the saving of the file succeeded.
            Episode episode = new Episode(uniqueID, inputData.getTitle(), inputData.getDescription(), null, null);
            episodeDAO.saveEpisode(episode);
            outputBoundary.prepareSuccessView(new UploadOutputData(episode, false));
        } else {
            outputBoundary.prepareFailView("Failed to upload file.");
        }
    }
}
