package use_case.podcast;

import data_access.PodcastDataAccess;
import entities.MediaItem;
import entities.Podcast;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class PodcastInteractorTest {
    @Test
    public void TestPodcastInteractor() {
        UUID id = UUID.randomUUID();
        Podcast podcast = new Podcast(id, "title", "description", null, null);
        PodcastInputData inputData = new PodcastInputData(id);
        PodcastDataAccess podcastDAO = new PodcastDataAccess() {
            @Override
            public boolean savePodcast(Podcast podcast) {
                return false;
            }

            @Override
            public Podcast getPodcastById(UUID id) {
                return podcast;
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
        PodcastOutputBoundary outputBoundary = new PodcastOutputBoundary() {
            @Override
            public void prepareSuccessView(PodcastOutputData outputData) {
                assertNotNull(outputData.getPodcast());
                return;
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };
        PodcastInteractor interactor = new PodcastInteractor(outputBoundary, podcastDAO);
        interactor.execute(inputData);
    }
    @Test
    public void TestPodcastNotFound() {
        UUID id = UUID.randomUUID();
        Podcast podcast = new Podcast(id, "title", "description", null, null);
        PodcastInputData inputData = new PodcastInputData(id);
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
        PodcastOutputBoundary outputBoundary = new PodcastOutputBoundary() {
            @Override
            public void prepareSuccessView(PodcastOutputData outputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                return;
            }
        };
        PodcastInteractor interactor = new PodcastInteractor(outputBoundary, podcastDAO);
        interactor.execute(inputData);
    }
}
