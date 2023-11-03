package entities;

import java.util.List;

public class Transcript {
    private final String text;
    private final List<TextChunk> textChunks;

    public Transcript(String text, List<TextChunk> textChunks) {
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
