package entities;

import java.net.URI;
import java.util.UUID;

public class Episode extends MediaItem {
    private Transcript transcript;
    private final String summary;

    public Episode(UUID id, String title, String description, URI audioLocation, Transcript transcript, String summary) {
        super(id, title, description, audioLocation);

        this.transcript = transcript;
        this.summary = summary;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public String getSummary() {
        return summary;
    }
    
    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }
}
