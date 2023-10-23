package entities;

import java.util.ArrayList;

public class Transcript {
    private final String text;
    private final ArrayList<TextChunk> textChunks;

    public Transcript(String text, ArrayList<TextChunk> textChunks) {
        this.text = text;
        this.textChunks = textChunks;
    }

    public String getText() {
        return text;
    }

    public ArrayList<TextChunk> getTextChunks() {
        return textChunks;
    }
}
