package interface_adapter.episode;

import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import entities.Episode;
import entities.TextChunk;
import entities.Transcript;
import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Test;
import use_case.episode.EpisodeInputBoundary;
import use_case.episode.EpisodeInteractor;
import use_case.episode.EpisodeOutputBoundary;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EpisodeEndToEndTest {
    @Test
    public void EpisodeEndToEndTest() {
        UUID id = UUID.randomUUID();
        List<TextChunk> textChunkList = new ArrayList<>();
        textChunkList.add(new TextChunk(0, 1, "hello"));
        Transcript transcript = new Transcript(UUID.randomUUID(), "text", textChunkList);
        Episode episode = new Episode(id, "cool episode", "test", transcript, null);
        EpisodeViewModel episodeViewModel = new EpisodeViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        EpisodeDataAccess episodeDataAccess = new EpisodeDataAccess() {
            @Override
            public boolean saveFile(URI fileLocation, UUID uniqueID) {
                return false;
            }

            @Override
            public File getFileById(UUID id) {
                return null;
            }

            @Override
            public boolean saveEpisode(Episode episode) {
                return false;
            }

            @Override
            public Episode getEpisodeById(UUID id) {
                return episode;
            }
        };
        EpisodeOutputBoundary presenter = new EpisodePresenter(viewManagerModel, episodeViewModel, podcastViewModel);
        EpisodeInputBoundary interactor = new EpisodeInteractor(episodeDataAccess, presenter);
        EpisodeController controller = new EpisodeController(interactor);
        controller.execute(id, null);
        assert episodeViewModel.getState().getCurrentEpisode().equals(episode);
    }
}
