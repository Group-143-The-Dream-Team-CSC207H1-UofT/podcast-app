package interface_adapter.episode;

import entities.Episode;
import entities.TextChunk;

import java.util.List;

public class EpisodeState {

    private Episode currentEpisode;
    private List<TextChunk> textChunks;
    private int currentTextChunkIndex;
    public EpisodeState(Episode currentEpisode, List<TextChunk> textChunks, int currentTextChunkIndex){
        this.currentEpisode = currentEpisode;
        this.textChunks = textChunks;
        this.currentTextChunkIndex = currentTextChunkIndex;
    }
    public EpisodeState(EpisodeState copy){
        this.currentEpisode = copy.currentEpisode;
        this.textChunks = copy.textChunks;
        this.currentTextChunkIndex = copy.currentTextChunkIndex;
    }
    public EpisodeState(){}

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
