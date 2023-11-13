package use_case.summary;

import entities.Episode;

public class SummaryOutputData {
    Episode episode;
    boolean useCaseFailed;

    public SummaryOutputData(Episode episode, boolean useCaseFailed) {
        this.episode = episode;
        this.useCaseFailed = useCaseFailed;
    }
}
