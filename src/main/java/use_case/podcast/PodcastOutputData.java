package use_case.podcast;

import entities.Podcast;

public class PodcastOutputData {
    private final Podcast podcast;
    private final boolean useCaseFailed;

    public PodcastOutputData(Podcast podcast, boolean useCaseFailed) {
        this.podcast = podcast;
        this.useCaseFailed = useCaseFailed;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
