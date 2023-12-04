package entities;

import java.net.URI;
import java.util.UUID;

public class Episode extends MediaItem {
  
    private Transcript transcript;
    private String summary;
    private UUID podcastUUID;

    public Episode(UUID id, UUID podcastUUID, String title, String description, Transcript transcript, String summary) {
        super(id, title, description);

        this.transcript = transcript;
        this.summary = summary;
        this.podcastUUID = podcastUUID;
    }



    public Transcript getTranscript() {
        return transcript;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }
    public void setPodcastUUID(UUID podcastUUID){this.podcastUUID = podcastUUID;}
    public UUID getPodcastUUID(){return podcastUUID;}

}
