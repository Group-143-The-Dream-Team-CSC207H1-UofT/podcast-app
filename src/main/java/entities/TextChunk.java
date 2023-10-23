package entities;

public class TextChunk {
    private final int start;
    private final int end;
    private final String text;

    public TextChunk(int start, int end, String text) {
        this.start = start;
        this.end = end;
        this.text = text;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getText() {
        return text;
    }
}
