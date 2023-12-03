package app;

import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.PodcastDataAccess;
import data_access.TranscriptDataAccessObject;
import entities.MediaItem;
import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Test;
import view.PodcastView;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PodcastViewFactoryTest {
    @Test
    public void TestPodcastViewFactory() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        EpisodeViewModel episodeViewModel = new EpisodeViewModel();
        CreateEpisodeViewModel createEpisodeViewModel = new CreateEpisodeViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        PodcastDataAccess podcastDataAccess = new PodcastDataAccess() {
            @Override
            public boolean savePodcast(Podcast podcast) {
                return false;
            }

            @Override
            public Podcast getPodcastById(UUID id) {
                return null;
            }

            @Override
            public List<MediaItem> getEpisodesForPodcast(UUID podcastId) {
                return null;
            }

            @Override
            public Collection<Podcast> getAllPodcasts() {
                return null;
            }
        };
        PodcastView view = PodcastViewFactory.create(viewManagerModel, podcastViewModel, episodeViewModel, createEpisodeViewModel, homeViewModel, episodeDataAccess, podcastDataAccess);
        assertNotNull(view);
    }
}
