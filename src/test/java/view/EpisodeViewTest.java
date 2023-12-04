package view;

import api.SummaryAPIInterface;
import app.SummaryUseCaseFactory;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import entities.Episode;
import entities.Transcript;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeState;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.summary.SummaryController;
import org.junit.jupiter.api.*;import use_case.summary.SummaryInputBoundary;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

class EpisodeViewTest {
    private EpisodeView episodeView;
    private JFrame testFrame;

    @BeforeEach
    void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            EpisodeViewModel episodeViewModel = new EpisodeViewModel();
            SummaryAPIInterface summaryAPIInterface = new SummaryAPIInterface() {
                @Override
                public String generateSummary(Transcript transcript) throws IOException {
                    return null;
                }
            };
            EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());

            SummaryController summaryController = SummaryUseCaseFactory.create(episodeViewModel, summaryAPIInterface, episodeDataAccess);
            episodeViewModel.getState().setCurrentEpisode(new Episode(UUID.randomUUID(), UUID.randomUUID(), "Test Episode", "Test Description", null, null));

            episodeView = new EpisodeView(viewManagerModel, episodeViewModel, summaryController);
            testFrame = new JFrame();
            testFrame.setContentPane(episodeView.panel);
            testFrame.pack();
            testFrame.setVisible(true);
        });
    }

    @AfterEach
    void tearDown() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(testFrame::dispose);
    }

    @Test
    void testEpisodeViewComponents() {
        assertNotNull(episodeView.panel, "Panel should not be null");
        assertNotNull(episodeView.titleLabel, "titleLabel should not be null");
    }


}
