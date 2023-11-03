package entities;

public class Episode {
    private final String title;
    // What kind of thing should audioLocation be, considering that we want to keep it abstract?
    // We just need to have a method or an interface which defines something like load() and store()
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
