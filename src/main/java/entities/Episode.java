package entities;

import javafx.scene.media.Media;

public class Episode {
    private final String title;
    // What kind of thing should audioLocation be, considering that we want to keep it abstract?
    private final String audioLocation;
    private final Transcript transcript;
    private final String summary;

    public Episode(String title, String audioLocation, Transcript transcript, String summary) {
        this.title = title;
        this.audioLocation = audioLocation;
        this.transcript = transcript;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public String getAudioLocation() {
        return audioLocation;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public String getSummary() {
        return summary;
    }
}
