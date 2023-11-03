package entities;

public class Episode extends MediaItem {
    // What kind of thing should audioLocation be, considering that we want to keep it abstract?
    // We just need to have a method or an interface which defines something like load() and store()
    private final String audioLocation;
    private final Transcript transcript;
    private final String summary;

    public Episode(int id, String title, String description, String audioLocation, Transcript transcript, String summary) {
        super(id, title, description);
        this.audioLocation = audioLocation;
        this.transcript = transcript;
        this.summary = summary;
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
