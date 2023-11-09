package data_access;

import entities.Episode;
import java.io.File;
import java.net.URI;
import java.util.UUID;

public interface EpisodeDataAccess {
    public boolean saveFile(URI fileLocation, UUID uniqueID);

    public File getFileById(UUID id);

    public boolean saveEpisode(Episode episode);

    public Episode getEpisodeById(UUID id);
}
