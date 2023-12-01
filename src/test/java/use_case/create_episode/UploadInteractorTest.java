package use_case.create_episode;

import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import entities.Episode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.fail;

class UploadInteractorTest {
    @Test
    public void TestUploadUseCase() throws URISyntaxException {
        UploadInputData inputData = new UploadInputData("David's Episode", "A dummy item for testing.",
                this.getClass().getResource( "/test.wav" ).toURI());
        UploadOutputBoundary outputBoundary = new UploadOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadOutputData outputData) {
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

        UploadInteractor interactor = new UploadInteractor(outputBoundary, new EpisodeDataAccessObject(new TranscriptDataAccessObject()));
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