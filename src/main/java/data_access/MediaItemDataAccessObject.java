package data_access;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.UUID;

public class MediaItemDataAccessObject implements MediaItemDataAccess {
    @Override
    public boolean saveFile(URI fileLocation, UUID uniqueID) {
        // get the path of the source file
        Path srcPath = Paths.get(fileLocation);

        // get the URI of the destination directory
        URI destURI;
        try {
            destURI = new URI(this.getClass().getResource("/").toURI() + "/audioFiles/");
        } catch (URISyntaxException e) {
            System.out.println("URI syntax error.");
            e.printStackTrace();
            return false;
        }

        // create the directory doesn't already exist
        File destDir = new File(destURI);
        destDir.mkdir();

        // get the path of the destination directory
        Path destDirPath = Paths.get(destURI);
        try {
            Files.copy(srcPath, destDirPath.resolve(uniqueID.toString() + ".wav"));
            return true;
        } catch (IOException e) {
            System.out.println("Failed to save file.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getFileByUUID(UUID uuid) {
        try {
            return new File(this.getClass().getResource("/audioFiles/" + uuid + ".wav").toString());
        } catch (NullPointerException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
            return null;
        }
    }
}
