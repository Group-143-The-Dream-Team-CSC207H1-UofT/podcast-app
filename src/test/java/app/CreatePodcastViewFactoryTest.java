package app;

import data_access.PodcastDataAccess;
import entities.MediaItem;
import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_podcast.CreatePodcastViewModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Test;
import view.CreatePodcastView;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatePodcastViewFactoryTest {
    @Test
    public void TestCreatePodcastViewFactory() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreatePodcastViewModel createPodcastViewModel = new CreatePodcastViewModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        PodcastDataAccess podcastDataAccess = new PodcastDataAccess() {
            @Override
            public boolean savePodcast(Podcast podcast) {
                return true;
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
        CreatePodcastView view = CreatePodcastViewFactory.create(viewManagerModel, createPodcastViewModel, podcastViewModel, podcastDataAccess);
        assertNotNull(view);
    }
}
