package app;

import api.ChatGPTSummary;
import api.SummaryAPIInterface;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.summary.SummaryController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SummaryUseCaseFactoryTest {
    @Test
    public void TestSummaryUseCaseFactory() {
        EpisodeViewModel episodeViewModel = new EpisodeViewModel();
        SummaryAPIInterface summaryAPI = new ChatGPTSummary(System.getenv("OPENAI_API_KEY"));
        EpisodeDataAccess episodeDataAccess = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        SummaryController controller = SummaryUseCaseFactory.create(episodeViewModel, summaryAPI, episodeDataAccess);
        assertNotNull(controller);
    }
}
