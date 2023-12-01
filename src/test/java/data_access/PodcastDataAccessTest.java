package data_access;

import entities.Episode;
import entities.MediaItem;
import entities.Podcast;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PodcastDataAccessTest {

    @Test
    public void TestSavePodcast() {
        UUID id = UUID.randomUUID();
        ArrayList<MediaItem> episodes = new ArrayList<>();
        episodes.add(new Episode(UUID.randomUUID(), "test episode", "description", null, null));
        Podcast podcast = new Podcast(id, "test podcast", null, episodes);
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        episodeDAO.saveEpisode((Episode) episodes.get(0));
        PodcastDataAccessObject podcastDAO = new PodcastDataAccessObject(episodeDAO, null);
        podcastDAO.savePodcast(podcast);
        Podcast newPodcast = podcastDAO.getPodcastById(id);
        assert podcast == newPodcast;
        List<MediaItem> newEpisodes = podcastDAO.getEpisodesForPodcast(id);
        assert newEpisodes.equals(episodes);
    }
}
