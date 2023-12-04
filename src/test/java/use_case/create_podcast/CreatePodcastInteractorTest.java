package use_case.create_podcast;

import data_access.PodcastDataAccess;
import entities.MediaItem;
import entities.Podcast;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class CreatePodcastInteractorTest {
    @Test
    public void TestCreatePodcastInteractor() {
        CreatePodcastInputData inputData = new CreatePodcastInputData("Test", "description");
        PodcastDataAccess podcastDAO = new PodcastDataAccess() {
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
        CreatePodcastOutputBoundary outputBoundary = new CreatePodcastOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePodcastOutputData outputData) {
                assertNotNull(outputData.getPodcast());
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };
        CreatePodcastInteractor interactor = new CreatePodcastInteractor(outputBoundary, podcastDAO);
        interactor.execute(inputData);
    }
}
