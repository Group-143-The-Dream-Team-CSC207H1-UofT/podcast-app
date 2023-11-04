package use_case.upload;

import data_access.MediaItemDataAccessObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

class UploadInteractorTest {
    @Test
    public void TestMain() {
        System.out.println();
        UploadInputData inputData = new UploadInputData("David's Episode", "A dummy item for testing.", "src/test/resources/test.wav");
        UploadInteractor interactor = new UploadInteractor(new MediaItemDataAccessObject());
        UploadOutputData outputData = interactor.execute(inputData);
        assert(outputData.getEpisode().getTitle().equals("David's Episode"));
        assert(outputData.getEpisode().getItemDescription().equals("A dummy item for testing."));
        File dest = new File("src/test/resources/audioFiles/test.wav");
        assert(dest.exists());
    }
}