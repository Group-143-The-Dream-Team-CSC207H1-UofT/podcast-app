package data_access;
import entities.Episode;
import entities.TextChunk;
import entities.Transcript;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class EpisodeDataAccessTest {
    @Test
    public void TestSaveEpisode() {
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        Transcript transcript = new Transcript(UUID.randomUUID(), "hello", new ArrayList<TextChunk>());
        Episode episode = new Episode(UUID.randomUUID(), "test", "test", transcript, null);
        episodeDAO.saveEpisode(episode);
        Episode newEpisode = episodeDAO.getEpisodeById(episode.getId());
        assert newEpisode == episode;
    }
    @Test
    public void TestFileDoesntExist() {
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        Transcript transcript = new Transcript(UUID.randomUUID(), "hello", new ArrayList<TextChunk>());
        Episode episode = new Episode(UUID.randomUUID(), "test", "test", transcript, null);
        episodeDAO.saveEpisode(episode);
        File file = episodeDAO.getFileById(episode.getId());
        assert file == null;
    }
    @Test public void TestSaveFile() throws URISyntaxException {
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        URI file = this.getClass().getResource( "/test.wav" ).toURI();
        UUID id = UUID.randomUUID();
        if(episodeDAO.saveFile(file, id)) {
            return;
        } else {
            fail();
        }
    }
}
