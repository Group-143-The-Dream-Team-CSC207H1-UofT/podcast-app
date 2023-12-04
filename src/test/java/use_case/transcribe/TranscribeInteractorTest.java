package use_case.transcribe;

import api.TranscriptionInterface;
import api.WhisperTranscription;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccess;
import data_access.TranscriptDataAccessObject;
import entities.Episode;
import entities.TextChunk;
import entities.Transcript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TranscribeInteractorTest {

    private TranscribeInteractor interactor;
    private Episode episode;
    private File audioFile;

    @BeforeEach
    void setup() {
        TranscriptDataAccess transcriptDAO = new TranscriptDataAccess() {
            @Override
            public boolean saveTranscript(Transcript transcript) {
                return true;
            }

            @Override
            public Transcript getTranscriptById(UUID id) {
                return null;
            }

            @Override
            public List<TextChunk> stringToChunks(String content) {
                return null;
            }
        };
        EpisodeDataAccess episodeDAO = new EpisodeDataAccessObject(transcriptDAO) {
            @Override
            public File getFileById(UUID id) {
                try {
                    audioFile = new File(this.getClass().getResource("/test.wav").toURI());
                } catch (NullPointerException | URISyntaxException e) {
                    System.out.println("failed to retrieve audiofile");
                }
                return audioFile;
            }
        };

        TranscriptionInterface transcriptionObject = new TranscriptionInterface() {
            @Override
            public String transcribeFile(File file) throws IOException {
                return "test transcription";
            }
        };

        TranscribeOutputBoundary outputBoundary = new TranscribeOutputBoundary() {
            @Override
            public void prepareSuccessView(TranscribeOutputData outputData) {
                Transcript transcript = outputData.getEpisode().getTranscript();
                assertNotNull(transcript);
                assertNotNull(episodeDAO.getEpisodeById(episode.getId()), "episode should be retrievable after being saved");
//                assertNotNull(transcriptDAO.getTranscriptById(episode.getId()), "transcript should be retrievable after being saved");
//                assertEquals(episode.getId(), transcript.getId(), "Outputted transcript should have the same ID as the input episode");
//                assertNotNull(transcript.getTextChunks());
            }

            @Override
            public void prepareFailView(String error) {
                fail(error);
            }
        };

        UUID episodeId = UUID.randomUUID();
        episode = new Episode(episodeId, UUID.randomUUID(),"Test Episode", "Test Description", null, null);

        interactor = new TranscribeInteractor(outputBoundary, episodeDAO, transcriptDAO, transcriptionObject);
    }

    @Test
    void expectTranscriptionToSucceed() {
        interactor.execute(new TranscribeInputData(episode));
    }

}
