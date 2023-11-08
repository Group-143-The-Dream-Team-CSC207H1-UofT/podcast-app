package use_case.transcribe;

import api.WhisperTranscription;
import data_access.MediaItemDataAccess;
import entities.Episode;
import entities.Transcript;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.util.UUID;

public class TranscribeInteractor implements TranscribeInputBoundary {
    private final TranscribeOutputBoundary outputBoundary;
    private final MediaItemDataAccess mediaItemDAO;

    public TranscribeInteractor(TranscribeOutputBoundary outputBoundary, MediaItemDataAccess mediaItemDAO) {
        this.outputBoundary = outputBoundary;
        this.mediaItemDAO = mediaItemDAO;
    }

    public void execute(TranscribeInputData inputData) {
        Episode episode = inputData.getEpisode();
        UUID epsUUID = episode.getId();
        File audioFile = mediaItemDAO.getFileByUUID(epsUUID); // TODO: do we need to handle exception here?
        WhisperTranscription apiWrapper = new WhisperTranscription();
        // TODO: make apiWrapper return textChunks so we can set them in episode transcript
        // for now this only generates raw text
        try {
            String transcriptString = apiWrapper.transcribeFile(audioFile);
            Transcript transcript = new Transcript(transcriptString, null);
            episode.setTranscript(transcript);

            outputBoundary.prepareSuccessView(new TranscribeOutputData(episode, false));
        }
        catch (IOException e) {outputBoundary.prepareFailView("Failed to transcribe file.");}





    }
}
