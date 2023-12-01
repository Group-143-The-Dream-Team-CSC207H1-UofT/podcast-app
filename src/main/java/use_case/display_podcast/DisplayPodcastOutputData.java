package use_case.display_podcast;

import entities.Podcast;

public class DisplayPodcastOutputData {
    private final Podcast podcast;
    private final boolean useCaseFailed;

    public DisplayPodcastOutputData(Podcast podcast, boolean useCaseFailed) {
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
