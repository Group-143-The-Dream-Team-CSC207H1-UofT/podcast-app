package use_case.summary;

import api.SummaryAPIInterface;
import data_access.EpisodeDataAccess;
import entities.Episode;
import entities.Transcript;

import java.io.IOException;

public class SummaryInteractor implements SummaryInputBoundary {
    SummaryOutputBoundary outputBoundary;
    EpisodeDataAccess episodeDataAccessObject;
    @Override
    public void execute(SummaryInputData inputData, SummaryAPIInterface APIWrapper) {
        Episode episode = inputData.getEpisode();
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
    }
}
