package use_case.episode;

import data_access.EpisodeDataAccess;
import entities.Episode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class EpisodeInteractorTest {
    @Test
    public void TestEpisodeInteractor() {
        UUID id = UUID.randomUUID();
        Episode episode = new Episode(id, "test", "description", null, null);
        EpisodeInputData inputData = new EpisodeInputData(id, null);
        EpisodeOutputBoundary outputBoundary = new EpisodeOutputBoundary() {
            @Override
            public void prepareSuccessView(EpisodeOutputData episodeOutputData) {
                assertNotNull(episodeOutputData.getEpisode());
                assert(!episodeOutputData.isUseCaseFailed());
                return;
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };
        EpisodeDataAccess episodeDAO = new EpisodeDataAccess() {
            @Override
            public boolean saveFile(URI fileLocation, UUID uniqueID) {
                return true;
            }

            @Override
            public File getFileById(UUID id) {
                return null;
            }

            @Override
            public boolean saveEpisode(Episode episode) {
                return true;
            }

            @Override
            public Episode getEpisodeById(UUID id) {
                return episode;
            }
        };
        EpisodeInteractor interactor = new EpisodeInteractor(episodeDAO, outputBoundary);
        interactor.execute(inputData);
    }
    @Test
    public void TestEpisodeNotFound() {
        UUID id = UUID.randomUUID();
        Episode episode = new Episode(id, "test", "description", null, null);
        EpisodeInputData inputData = new EpisodeInputData(id, null);
        EpisodeOutputBoundary outputBoundary = new EpisodeOutputBoundary() {
            @Override
            public void prepareSuccessView(EpisodeOutputData episodeOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                return;
            }
        };
        EpisodeDataAccess episodeDAO = new EpisodeDataAccess() {
            @Override
            public boolean saveFile(URI fileLocation, UUID uniqueID) {
                return true;
            }

            @Override
            public File getFileById(UUID id) {
                return null;
            }

            @Override
            public boolean saveEpisode(Episode episode) {
                return true;
            }

            @Override
            public Episode getEpisodeById(UUID id) {
                return null;
            }
        };
        EpisodeInteractor interactor = new EpisodeInteractor(episodeDAO, outputBoundary);
        interactor.execute(inputData);
    }
}
