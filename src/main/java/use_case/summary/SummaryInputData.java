package use_case.summary;

import entities.Episode;

public class SummaryInputData {
    Episode episode;

    public SummaryInputData(Episode episode) {
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
