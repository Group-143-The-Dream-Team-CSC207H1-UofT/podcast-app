package use_case.episode;

import entities.Episode;
import entities.Podcast;
import entities.TextChunk;

public class EpisodeOutputData {

    private final Episode episode;
    private final TextChunk currentTextChunk;
    private final boolean useCaseFailed;
    private final Podcast podcast;
    public EpisodeOutputData(Episode episode, Podcast podcast, TextChunk currentTextChunk, boolean useCaseFailed){
        this.episode = episode;
        this.currentTextChunk = currentTextChunk;
        this.useCaseFailed = useCaseFailed;
        this.podcast = podcast;
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

    public Podcast getPodcast() {
        return podcast;
    }
}
