package use_case.create_episode;

import data_access.EpisodeDataAccess;
import entities.Episode;
import java.util.UUID;

public class CreateEpisodeInteractor implements CreateEpisodeInputBoundary {
    private final CreateEpisodeOutputBoundary outputBoundary;
    private final EpisodeDataAccess episodeDAO;

    public CreateEpisodeInteractor(CreateEpisodeOutputBoundary outputBoundary, EpisodeDataAccess episodeDAO) {
        this.outputBoundary = outputBoundary;
        this.episodeDAO = episodeDAO;
    }

    public void execute(CreateEpisodeInputData inputData) {
        UUID uniqueID = UUID.randomUUID();
        if (episodeDAO.saveFile(inputData.getAudioFileURI(), uniqueID)) {
            // the saving of the file succeeded.
            Episode episode = new Episode(uniqueID, inputData.getTitle(), inputData.getDescription(), null, null);
            episodeDAO.saveEpisode(episode);
            outputBoundary.prepareSuccessView(new CreateEpisodeOutputData(episode));
        } else {
            outputBoundary.prepareFailView("Failed to create episode.");
        }
    }
}
