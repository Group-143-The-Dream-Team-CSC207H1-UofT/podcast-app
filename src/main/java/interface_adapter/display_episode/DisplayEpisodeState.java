package interface_adapter.display_episode;

import entities.Episode;
import entities.TextChunk;

import java.util.List;

public class DisplayEpisodeState {

    private Episode currentEpisode;
    private List<TextChunk> textChunks;
    private int currentTextChunkIndex;
    public DisplayEpisodeState(Episode currentEpisode, List<TextChunk> textChunks, int currentTextChunkIndex){
        this.currentEpisode = currentEpisode;
        this.textChunks = textChunks;
        this.currentTextChunkIndex = currentTextChunkIndex;
    }
    public DisplayEpisodeState(DisplayEpisodeState copy){
        this.currentEpisode = copy.currentEpisode;
        this.textChunks = copy.textChunks;
        this.currentTextChunkIndex = copy.currentTextChunkIndex;
    }
    public DisplayEpisodeState(){}

    public Episode getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(Episode currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public int getCurrentTextChunkIndex() {
        return currentTextChunkIndex;
    }

    public void setCurrentTextChunkIndex(int currentTextChunkIndex){
        this.currentTextChunkIndex = currentTextChunkIndex;
    }
    public List<TextChunk> getTextChunks() {
        return textChunks;
    }

    public void setTextChunks(List<TextChunk> textChunks) {
        this.textChunks = textChunks;
    }
}
