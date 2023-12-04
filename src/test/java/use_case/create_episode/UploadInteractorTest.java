package use_case.create_episode;

import data_access.*;
import entities.Episode;
import entities.MediaItem;
import entities.Podcast;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

class UploadInteractorTest {
    @Test
    public void TestUploadUseCase() throws URISyntaxException {
        CreateEpisodeInputData inputData = new CreateEpisodeInputData("David's Episode", "A dummy item for testing.",
                this.getClass().getResource( "/test.wav" ).toURI(), UUID.randomUUID());
        CreateEpisodeOutputBoundary outputBoundary = new CreateEpisodeOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateEpisodeOutputData outputData) {
                Episode episode = outputData.getEpisode();
                assert(episode.getTitle().equals("David's Episode"));
                assert(episode.getItemDescription().equals("A dummy item for testing."));
                try {
                    File dest = new File(
                            this.getClass().getResource("/audioFiles/" + episode.getId() + ".wav").toURI());
                    assert(dest.exists());
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Override
            public void prepareFailView(String error) {
                fail(error);
            }
        };
        EpisodeDataAccess episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        PodcastDataAccess podcastDataAccess = new PodcastDataAccess() {
            @Override
            public boolean savePodcast(Podcast podcast) {
                return true;
            }

            @Override
            public Podcast getPodcastById(UUID id) {
                return new Podcast(UUID.randomUUID(), "name", "description", null, new ArrayList<>());
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
        CreateEpisodeInteractor interactor = new CreateEpisodeInteractor(outputBoundary, episodeDAO, podcastDataAccess);
        interactor.execute(inputData);
    }

    @AfterAll
    static void removeAudioFiles() {
        try {
            File audioFilesDir = new File(UploadInteractorTest.class.getResource( "/audioFiles/").toURI());
            FileUtils.deleteDirectory(audioFilesDir);
        } catch (URISyntaxException e) {
            System.out.println("Failed to get the audioFiles directory URI.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to delete audioFiles directory");
            e.printStackTrace();
        }
    }
}