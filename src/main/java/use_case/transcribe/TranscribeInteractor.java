package use_case.transcribe;

import api.TranscriptionInterface;
import api.WhisperTranscription;
import data_access.EpisodeDataAccess;
import data_access.TranscriptDataAccess;
import entities.Episode;
import entities.TextChunk;
import entities.Transcript;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class TranscribeInteractor implements TranscribeInputBoundary {
    private final TranscribeOutputBoundary outputBoundary;
    private final TranscriptDataAccess transcriptDAO;
    private final EpisodeDataAccess episodeDAO;

    private final TranscriptionInterface transcriptionObject;

    public TranscribeInteractor(TranscribeOutputBoundary outputBoundary, EpisodeDataAccess episodeDAO, TranscriptDataAccess transcriptDAO, TranscriptionInterface transcriptionObject) {
        this.outputBoundary = outputBoundary;
        this.episodeDAO = episodeDAO;
        this.transcriptDAO = transcriptDAO;
        this.transcriptionObject = transcriptionObject;
    }
    /**
     * {@inheritDoc}
     */
    public void execute(TranscribeInputData inputData) {
        Episode episode = inputData.getEpisode();
        UUID epsUUID = episode.getId();
        File audioFile = episodeDAO.getFileById(epsUUID);  // TODO: do we need to handle exception here?
        try {
            String transcriptString = transcriptionObject.transcribeFile(audioFile);
            List<TextChunk> textChunks = transcriptDAO.stringToChunks(transcriptString);
            Transcript transcript = new Transcript(epsUUID, transcriptString, textChunks);
            episode.setTranscript(transcript);
            if (!transcriptDAO.saveTranscript(transcript) || !episodeDAO.saveEpisode(episode)) {
                outputBoundary.prepareFailView("Failed to save after transcription");
            };
            outputBoundary.prepareSuccessView(new TranscribeOutputData(episode, false));
        }
        catch (IOException e) {outputBoundary.prepareFailView("Failed to transcribe file.");}
    }
}
