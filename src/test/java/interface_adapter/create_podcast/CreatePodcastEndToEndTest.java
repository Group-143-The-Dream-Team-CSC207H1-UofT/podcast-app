package interface_adapter.create_podcast;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.create_podcast.CreatePodcastInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.Assert.assertEquals;

public class CreatePodcastEndToEndTest {

    private ViewManagerModel viewManagerModel;
    private CreatePodcastViewModel createPodcastViewModel;
    private PodcastViewModel podcastViewModel;
    private CreatePodcastPresenter presenter;
    private EpisodeDataAccess episodeDataAccess;
    private PodcastDataAccess podcastDataAccess;
    private CreatePodcastInteractor interactor;
    private CreatePodcastController controller;

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        createPodcastViewModel = new CreatePodcastViewModel();
        podcastViewModel = new PodcastViewModel();
        presenter = new CreatePodcastPresenter(createPodcastViewModel, podcastViewModel, viewManagerModel);
        episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        podcastDataAccess = new PodcastDataAccessObject(episodeDataAccess);
        interactor = new CreatePodcastInteractor(presenter, podcastDataAccess);
        controller = new CreatePodcastController(interactor);
    }
    @Test
    public void EndToEndTest() {
        controller.execute("cool podcast", "nice :)");
        Assertions.assertEquals("cool podcast", podcastViewModel.getState().getCurrentPodcast().getName());
    }
    @AfterEach
    public void tearDown() {
        if (episodeDataAccess instanceof EpisodeDataAccessObject) {
            ((EpisodeDataAccessObject) episodeDataAccess).clearData();
        }
        if (podcastDataAccess instanceof PodcastDataAccessObject) {
            ((PodcastDataAccessObject) podcastDataAccess).clearData();
        }

    }

}
