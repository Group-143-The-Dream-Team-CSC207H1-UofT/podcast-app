package use_case.summary;

import api.ChatGPTSummary;
import api.SummaryAPIInterface;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccessObject;
import entities.Episode;
import entities.Transcript;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class SummaryInteractorTest {
    @Test
    public void TestSummaryAlreadyExists() {
        UUID id = UUID.randomUUID();
        Transcript transcript = new Transcript(id, "", new ArrayList<>());
        Episode episode = new Episode(id, UUID.randomUUID(), "test", "test", transcript, "The summary exists");
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

    @Test
    public void generateSummary() {
        UUID id = UUID.randomUUID();
        Transcript transcript = new Transcript(id, "1\n" +
                "00:05:00,400 --> 00:05:15,300\n" +
                "This is an example of a subtitle.\n" +
                "\n" +
                "2\n" +
                "00:05:16,400 --> 00:05:25,300\n" +
                "This is an example of a subtitle - 2nd subtitle.", new ArrayList<>());
        Episode episode = new Episode(id, UUID.randomUUID(), "test", "test", transcript, null);
        SummaryInputData inputData = new SummaryInputData(episode);
        SummaryOutputBoundary presenter = new SummaryOutputBoundary() {
            @Override
            public void prepareSuccessView(SummaryOutputData outputData) {
                if (outputData.useCaseFailed) {
                    fail();
                } else {
                    return;
                }
            }

            @Override
            public void prepareFailView(SummaryOutputData outputData) {
                fail();
            }
        };
        SummaryAPIInterface hardCodedSummary = new SummaryAPIInterface() {
            @Override
            public String generateSummary(Transcript transcript) throws IOException {
                return "Cool episode :)";
            }
        };
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        SummaryInteractor interactor = new SummaryInteractor(presenter, episodeDAO);
        interactor.execute(inputData, hardCodedSummary);
    }
    @Test
    public void TestIOException() {
        UUID id = UUID.randomUUID();
        Transcript transcript = new Transcript(id, "", new ArrayList<>());
        Episode episode = new Episode(id, UUID.randomUUID(), "test", "test", transcript, null);
        SummaryInputData inputData = new SummaryInputData(episode);
        SummaryOutputBoundary presenter = new SummaryOutputBoundary() {
            @Override
            public void prepareSuccessView(SummaryOutputData outputData) {
                fail();
            }

            @Override
            public void prepareFailView(SummaryOutputData outputData) {
                return;
            }
        };
        SummaryAPIInterface hardCodedError = new SummaryAPIInterface() {
            @Override
            public String generateSummary(Transcript transcript) throws IOException {
                throw new IOException();
            }
        };
        EpisodeDataAccessObject episodeDAO = new EpisodeDataAccessObject(new TranscriptDataAccessObject());
        SummaryInteractor interactor = new SummaryInteractor(presenter, episodeDAO);
        interactor.execute(inputData, hardCodedError);
    }
}
