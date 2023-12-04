package use_case.summary;

import api.SummaryAPIInterface;
import data_access.EpisodeDataAccess;
import entities.Episode;
import entities.Transcript;

import java.io.IOException;

public class SummaryInteractor implements SummaryInputBoundary {
    SummaryOutputBoundary outputBoundary;
    EpisodeDataAccess episodeDataAccessObject;

    public SummaryInteractor(SummaryOutputBoundary outputBoundary, EpisodeDataAccess episodeDAO) {
        this.outputBoundary = outputBoundary;
        episodeDataAccessObject = episodeDAO;
    }

    /**
     * Generates a summary of the given podcast using the provided API
     * @param inputData contains a podcast with a transcript
     * @param APIWrapper used to generate a summary of the podcast
     */
    @Override
    public void execute(SummaryInputData inputData, SummaryAPIInterface APIWrapper) {
        Episode episode = inputData.getEpisode();
        if (episode.getSummary() == null || episode.getSummary().isEmpty()) {
            try {
                String summary = APIWrapper.generateSummary(episode.getTranscript());
                episode.setSummary(summary);
                // once the summary is generated, set the summary and save the episode
                episodeDataAccessObject.saveEpisode(episode);
                SummaryOutputData outputData = new SummaryOutputData(episode, false);
                outputBoundary.prepareSuccessView(outputData);
            } catch (IOException e) {
                SummaryOutputData outputData = new SummaryOutputData(episode, true);
                outputBoundary.prepareFailView(outputData);
            }
        } else {
            // if the summary already exists, we don't want to waste an API call and regenerate it
            // we're going to prepare the success view because the summary does exist, but set useCaseFailed to true
            // in the presenter, we can have a check for the value of useCaseFailed
            // if useCaseFailed is true, display some kind of message saying that the summary already existed
            SummaryOutputData outputData = new SummaryOutputData(episode, true);
            outputBoundary.prepareSuccessView(outputData);
        }
    }
}
