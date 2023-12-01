package data_access;

import entities.Episode;
import entities.TextChunk;
import entities.Transcript;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
