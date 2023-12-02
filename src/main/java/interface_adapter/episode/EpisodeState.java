package interface_adapter.episode;

import entities.Episode;
import entities.TextChunk;

import java.util.List;

public class EpisodeState {

    private Episode currentEpisode;
    private List<TextChunk> textChunks;
    private TextChunk currentTextChunk;
    public EpisodeState(Episode currentEpisode, List<TextChunk> textChunks, TextChunk currentTextChunk){
        this.currentEpisode = currentEpisode;
        this.textChunks = textChunks;
        this.currentTextChunk = currentTextChunk;
    }
    public EpisodeState(EpisodeState copy){
        this.currentEpisode = copy.currentEpisode;
        this.textChunks = copy.textChunks;
        this.currentTextChunk = copy.currentTextChunk;
    }
    public EpisodeState(){}

    public Episode getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(Episode currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public TextChunk getCurrentTextChunk() {
        return currentTextChunk;
    }

    public void setCurrentTextChunk(TextChunk currentTextChunk){
        this.currentTextChunk = currentTextChunk;
    }
    public List<TextChunk> getTextChunks() {
        return textChunks;
    }

    public void setTextChunks(List<TextChunk> textChunks) {
        this.textChunks = textChunks;
    }
}
