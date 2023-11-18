package data_access;
import entities.TextChunk;
import entities.Transcript;
import java.util.List;
import java.util.UUID;

/**
 * Interface defining data access operations for transcripts.
 */
public interface TranscriptDataAccess {

    /**
     * Saves a transcript to the data store.
     *
     * @param transcript The transcript to save.
     * @return true if the transcript was saved successfully, false otherwise.
     */
    public boolean saveTranscript(Transcript transcript);

    /**
     * Retrieves a transcript by its unique identifier.
     *
     * @param id The UUID of the transcript.
     * @return The Transcript object associated with the ID, or null if not found.
     */
    public Transcript getTranscriptById(UUID id);

    /**
     * Converts a string representation of a transcript into a list of text chunks.
     *
     * @param content The string content of the transcript.
     * @return A list of TextChunk objects representing the content.
     */
    public List<TextChunk> stringToChunks(String content);
}
