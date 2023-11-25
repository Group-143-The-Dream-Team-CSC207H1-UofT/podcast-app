package use_case.display_episode;

import entities.Episode;

public class DisplayEpisodeOutputData {

    private final Episode episode;
    private final int currentTextChunkIndex;
    private final boolean useCaseFailed;
    public DisplayEpisodeOutputData(Episode episode, int currentTextChunkIndex, boolean useCaseFailed){
        this.episode = episode;
        this.currentTextChunkIndex = currentTextChunkIndex;
        this.useCaseFailed = useCaseFailed;
    }
    public Episode getEpisode(){
        return episode;
    }

    public int getCurrentTextChunkIndex() {
        return currentTextChunkIndex;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
