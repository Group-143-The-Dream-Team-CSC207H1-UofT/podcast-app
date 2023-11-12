package data_access;
import entities.*;
import java.util.*;


public interface PodcastDataAccessInterface {

    // User-related
    User getUserByUsername(String username);
    void saveUser(User user);

    // Podcast-related
    List<Podcast> getAllPodcastsForUser(String username);
    boolean savePodcast(Podcast podcast);

    // Episode-related
    List<Episode> getEpisodesForPodcast(String podcastName);
    void saveEpisode(Episode episode);

    // Transcript-related
    Transcript getTranscriptForEpisode(int episodeId);
    void saveTranscript(Transcript transcript);

    // Section-related
    List<Section> getSectionsForTranscript(int transcriptId);
    void saveSection(Section section);

    // TextChunk-related
    List<TextChunk> getTextChunksForSection(int sectionId);
    void saveTextChunk(TextChunk textChunk);

    // Media-related
    MediaCollection getMediaByName(String mediaName);
    void saveMedia(MediaCollection media);

    // MediaItem-related
    List<MediaItem> getMediaItemsForMedia(String mediaName);
    void saveMediaItem(MediaItem mediaItem);
}

