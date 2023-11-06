package use_case.upload;

import data_access.MediaItemDataAccessObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

class UploadInteractorTest {
    @Test
    public void TestMain() throws URISyntaxException {
        UploadInputData inputData = new UploadInputData("David's Episode", "A dummy item for testing.",
                this.getClass().getResource( "/test.wav" ).toURI());
        UploadInteractor interactor = new UploadInteractor(new MediaItemDataAccessObject());
//        UploadOutputData outputData = interactor.execute(inputData);
//        assert(outputData.getEpisode().getTitle().equals("David's Episode"));
//        assert(outputData.getEpisode().getItemDescription().equals("A dummy item for testing."));
//        File dest = new File(this.getClass().getResource( "/audioFiles/test.wav" ).toURI());
//        assert(dest.exists());
    }
}