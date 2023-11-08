package data_access;

import entities.Transcript;

import java.util.UUID;

public interface TranscriptDataAccess {
    public boolean saveTranscript(Transcript transcript);

    public Transcript getTranscriptById(UUID id);
}
