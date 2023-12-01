package use_case.create_episode;

import java.net.URI;
import java.util.UUID;

public class CreateEpisodeInputData {
    final private String title;
    final private String description;
    final private URI audioFileURI;
    final private UUID podcastUUID;

    public CreateEpisodeInputData(String title, String description, URI audioFileURI, UUID podcastUUID) {
        this.title = title;
        this.description = description;
        this.audioFileURI = audioFileURI;
        this.podcastUUID = podcastUUID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public URI getAudioFileURI() {
        return audioFileURI;
    }

    public UUID getPodcastUUID() {
        return podcastUUID;
    }
}
