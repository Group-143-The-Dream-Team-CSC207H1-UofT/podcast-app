package data_access;
import entities.TextChunk;
import entities.Transcript;
import java.util.List;
import java.util.UUID;

public interface TranscriptDataAccess {
    public boolean saveTranscript(Transcript transcript);

    public Transcript getTranscriptById(UUID id);
    public List<TextChunk> stringToChunks(String content);
}
