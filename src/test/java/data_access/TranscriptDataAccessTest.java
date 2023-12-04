package data_access;
import static org.junit.Assert.*;
import entities.Transcript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

public class TranscriptDataAccessTest {

    private TranscriptDataAccessObject transcriptDao;
    private Transcript transcript;

    @Before
    public void setUp() {
        transcriptDao = new TranscriptDataAccessObject();
        String sampleContent = "Speaker 1\n00:00:01,000 --> 00:00:05,000\nHello, welcome to our podcast.\n\n" +
                "Speaker 2\n00:00:06,000 --> 00:00:10,000\nThank you for having me.\n\n" +
                "Speaker 1\n00:00:11,000 --> 00:00:15,000\nLet's discuss the topic of the day.\n";
        transcript = new Transcript(UUID.randomUUID(), sampleContent , transcriptDao.stringToChunks(sampleContent));
    }

    @Test
    public void testSaveTranscript() {
        boolean result = transcriptDao.saveTranscript(transcript);
        assertTrue(result);
    }

    @Test
    public void testGetTranscriptById() {
        transcriptDao.saveTranscript(transcript);
        Transcript retrieved = transcriptDao.getTranscriptById(transcript.getId());
        assertEquals(retrieved, transcript);
    }

}
