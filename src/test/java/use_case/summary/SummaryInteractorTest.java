package use_case.summary;

import api.ChatGPTSummary;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import entities.Episode;
import entities.Transcript;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class SummaryInteractorTest {
    @Test
    public void TestSummaryAlreadyExists() {
        Transcript transcript = new Transcript(UUID.randomUUID(), "", new ArrayList<>());
        Episode episode = new Episode(UUID.randomUUID(), "test", "test", transcript, "The summary exists");
        SummaryInputData inputData = new SummaryInputData(episode);
        SummaryOutputBoundary presenter = new SummaryOutputBoundary() {
            @Override
            public void prepareSuccessView(SummaryOutputData outputData) {
                if (outputData.useCaseFailed) {
                    return;
                } else {
                    fail();
                }
            }

            @Override
            public void prepareFailView(SummaryOutputData outputData) {
                fail();
            }
        };
        ChatGPTSummary APIWrapper = new ChatGPTSummary("");
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        SummaryInteractor interactor = new SummaryInteractor(presenter, episodeDAO);
        interactor.execute(inputData, APIWrapper);
    }
}
