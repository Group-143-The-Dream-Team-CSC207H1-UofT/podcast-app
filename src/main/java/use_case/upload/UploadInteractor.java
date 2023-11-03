package use_case.upload;

import java.io.File;
import java.nio.file.Files;

public class UploadInteractor {
    public void execute(UploadInputData inputData) {
        File inputFile = new File(inputData.getAudioFilePath());
        File destinationFile = new File(String.format("audioFiles/%s", inputFile.getName()));

        // we need to create a DAO which will take the input file path as input and will create the copy in some
        // some subdirectory like "audioFiles"
    }
}
