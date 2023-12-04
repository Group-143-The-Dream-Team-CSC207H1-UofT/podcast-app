package view;

import app.PodcastViewFactory;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.PodcastDataAccessObject;
import data_access.TranscriptDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

class PodcastViewTest {
    private PodcastView podcastView;
    private JFrame testFrame;

    @BeforeEach
    void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            PodcastViewModel podcastViewModel = new PodcastViewModel();
            CreateEpisodeViewModel createEpisodeViewModel = new CreateEpisodeViewModel();
            EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
            podcastView = PodcastViewFactory.create(viewManagerModel, podcastViewModel, new EpisodeViewModel(), createEpisodeViewModel, new HomeViewModel(), new PodcastDataAccessObject(episodeDataAccess), episodeDataAccess);
            testFrame = new JFrame();
            testFrame.setContentPane(podcastView.panel);
            testFrame.pack();
            testFrame.setVisible(true);
        });
    }

    @AfterEach
    void tearDown() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(testFrame::dispose);
    }

    @Test
    void testPodcastViewComponents() {
        assertNotNull(podcastView.panel, "Panel should not be null");
        assertNotNull(podcastView.titleLabel, "titleLabel should not be null");
    }

}
