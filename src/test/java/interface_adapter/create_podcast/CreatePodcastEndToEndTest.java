package interface_adapter.create_podcast;

import data_access.*;
import entities.MediaItem;
import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Test;
import use_case.create_podcast.CreatePodcastInputData;
import use_case.create_podcast.CreatePodcastInteractor;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CreatePodcastEndToEndTest {

    @Test
    public void EndToEndTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreatePodcastViewModel createPodcastViewModel = new CreatePodcastViewModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        CreatePodcastPresenter presenter = new CreatePodcastPresenter(createPodcastViewModel, podcastViewModel, viewManagerModel);
        EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        PodcastDataAccess podcastDataAccess = new PodcastDataAccessObject(episodeDataAccess);
        CreatePodcastInteractor interactor = new CreatePodcastInteractor(presenter, podcastDataAccess);
        CreatePodcastController controller = new CreatePodcastController(interactor);
        CreatePodcastInputData inputData = new CreatePodcastInputData("cool podcast", "nice");
        controller.execute("cool podcast", "nice :)");
        assert podcastViewModel.getState().getCurrentPodcast().getName().equals("cool podcast");
    }
}
