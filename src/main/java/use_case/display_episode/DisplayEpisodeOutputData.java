package use_case.display_episode;

import entities.Episode;

public class DisplayEpisodeOutputData {

    private final Episode episode;
    private boolean useCaseFailed;
    public DisplayEpisodeOutputData(Episode episode, boolean useCaseFailed){
        this.episode = episode;
        this.useCaseFailed = useCaseFailed;
    }
    public Episode getEpisode(){
        return episode;
    }
}
