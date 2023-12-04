package view;

import app.CreatePodcastViewFactory;
import data_access.EpisodeDataAccessObject;
import data_access.PodcastDataAccess;
import data_access.PodcastDataAccessObject;
import data_access.TranscriptDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_podcast.CreatePodcastViewModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

class CreatePodcastViewTest {
    private CreatePodcastView createPodcastView;
    private JFrame testFrame;

    @BeforeEach
    void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            CreatePodcastViewModel viewModel = new CreatePodcastViewModel();
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            CreatePodcastViewModel createPodcastViewModel = new CreatePodcastViewModel();
            PodcastViewModel podcastViewModel = new PodcastViewModel();
            PodcastDataAccess podcastDataAccess = new PodcastDataAccessObject(new EpisodeDataAccessObject(new TranscriptDataAccessObject()));
            createPodcastView = CreatePodcastViewFactory.create(viewManagerModel, createPodcastViewModel, podcastViewModel, podcastDataAccess);
            testFrame = new JFrame();
            testFrame.add(createPodcastView);
            testFrame.pack();
            testFrame.setVisible(true);
        });
    }

    @AfterEach
    void tearDown() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(testFrame::dispose);
    }

    @Test
    void testCreatePodcastViewComponents() {
        assertNotNull(createPodcastView.titleInputField, "TitleInputField should not be null");
        assertNotNull(createPodcastView.descriptionInputField, "DescriptionInputField should not be null");
    }

}
