package entities;

public class Episode extends MediaItem {
    private final Transcript transcript;
    private final String summary;

    public Episode(int id, String title, String description, String audioLocation, Transcript transcript, String summary) {
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
}
