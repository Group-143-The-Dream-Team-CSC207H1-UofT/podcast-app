package entities;

import java.util.List;
import java.util.UUID;

public class Transcript {
    private final UUID Id;
    private final String text;
    private final List<TextChunk> textChunks;

    public UUID getId() {
        return Id;
    }

    public Transcript(UUID Id, String text, List<TextChunk> textChunks) {
        this.Id = Id;
        this.text = text;
        this.textChunks = textChunks;
    }

    public String getText() {
        return text;
    }

    public List<TextChunk> getTextChunks() {
        return textChunks;
    }
}
