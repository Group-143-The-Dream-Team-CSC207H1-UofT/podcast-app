package data_access;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class MediaItemDataAccessObject implements MediaItemDataAccess {
    @Override
    public boolean saveFile(String fileLocation) {
        File srcFile = new File(fileLocation);
        Path srcPath = Paths.get(fileLocation);
        Path destPath = Paths.get(String.format("src/test/resources/audioFiles/%s", srcFile.getName()));
        try {
            Files.copy(srcPath, destPath);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to save file.");
            e.printStackTrace();
            return false;
        }
    }
}
