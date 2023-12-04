package use_case.episode;

import entities.Episode;
import entities.TextChunk;

public class EpisodeOutputData {

    private final Episode episode;
    private final TextChunk currentTextChunk;
    private final boolean useCaseFailed;
    public EpisodeOutputData(Episode episode, TextChunk currentTextChunk, boolean useCaseFailed){
        this.episode = episode;
        this.currentTextChunk = currentTextChunk;
        this.useCaseFailed = useCaseFailed;
    }
    public Episode getEpisode(){
        return episode;
    }

    public TextChunk getCurrentTextChunk() {
        return currentTextChunk;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
