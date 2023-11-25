package data_access;

import entities.MediaItem;
import entities.Podcast;
import java.util.List;
import java.util.UUID;

public interface PodcastDataAccess {

    public boolean savePodcast(Podcast podcast);

    public Podcast getPodcastById(UUID id);

    public List<MediaItem> getEpisodesForPodcast(UUID podcastId);
}
