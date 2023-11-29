package use_case.display_podcasts;

import data_access.PodcastDataAccess;
import entities.MediaItem;
import entities.Podcast;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DisplayPodcastsInteractorTest {
    @Test
    public void TestGetPodcasts() {
        PodcastDataAccess podcastDAO = new PodcastDataAccess() {
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
                ArrayList<Podcast> allPodcasts = new ArrayList<>();
                allPodcasts.add(new Podcast(UUID.randomUUID(), "testPodcast", null, new ArrayList<>()));
                return allPodcasts;
            }
        };
        DisplayPodcastsOutputBoundary presenter = new DisplayPodcastsOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayPodcastsOutputData outputData) {
                assertNotNull(outputData.getAllPodcasts(), "should contain the podcast we added");
                return;
            }

            @Override
            public void prepareFailView(DisplayPodcastsOutputData outputData) {
                fail();
            }
        };
        DisplayPodcastsInteractor interactor = new DisplayPodcastsInteractor(presenter, podcastDAO);
        interactor.execute(new DisplayPodcastsInputData());
    }

    public void TestGetPodcastsFails() {
        PodcastDataAccess podcastDAO = new PodcastDataAccess() {
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
    }
}
