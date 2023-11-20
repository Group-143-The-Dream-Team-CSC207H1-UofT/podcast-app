package data_access;

import entities.Episode;
import java.io.File;
import java.net.URI;
import java.util.UUID;

/**
 * Interface for accessing episode data.
 */
public interface EpisodeDataAccess {
    /**
     * Saves an audio file to a specified location.
     *
     * @param fileLocation The URI location of the file to be saved.
     * @param uniqueID The unique identifier of the episode associated with the file.
     * @return true if the file was saved successfully, false otherwise.
     */
    public boolean saveFile(URI fileLocation, UUID uniqueID);

    /**
     * Retrieves the file associated with the given episode ID.
     *
     * @param id The UUID of the episode.
     * @return The File object for the episode's audio file, or null if not found.
     */
    public File getFileById(UUID id);

    /**
     * Saves episode data.
     *
     * @param episode The episode entity to be saved.
     * @return true if the episode was saved successfully, false otherwise.
     */
    public boolean saveEpisode(Episode episode);

    /**
     * Retrieves an episode by its ID.
     *
     * @param id The UUID of the episode.
     * @return The Episode object, or null if no episode with the given ID exists.
     */
    public Episode getEpisodeById(UUID id);
}
