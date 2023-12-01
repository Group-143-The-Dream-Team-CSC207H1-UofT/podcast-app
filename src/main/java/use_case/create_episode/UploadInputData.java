package use_case.create_episode;

import java.net.URI;

public class UploadInputData {
    final private String title;
    final private String description;
    final private URI audioFileURI;

    public UploadInputData(String title, String description, URI audioFileURI) {
        this.title = title;
        this.description = description;
        this.audioFileURI = audioFileURI;
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
}
