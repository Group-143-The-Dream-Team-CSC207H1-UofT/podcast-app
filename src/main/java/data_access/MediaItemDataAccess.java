package data_access;

import java.io.File;
import java.net.URI;
import java.util.UUID;

public interface MediaItemDataAccess {
    public boolean saveFile(URI fileLocation, UUID uniqueID);

    public File getFileByUUID(UUID uuid);
}
