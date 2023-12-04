package app;

import api.SummaryAPIInterface;
import data_access.EpisodeDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.summary.SummaryController;
import interface_adapter.summary.SummaryPresenter;
import use_case.summary.SummaryInteractor;

public class SummaryUseCaseFactory {
    /**
     * Creates a new SummaryController to be used in the EpisodeView
     * @param episodeViewModel the ViewModel for the EpisodeView
     * @param summaryAPI the specific SummaryAPIInterface desired
     * @param episodeDAO the implementation of the data access object for episodes desired
     * @return A controller for the summary use case.
     */
    public static SummaryController create(EpisodeViewModel episodeViewModel, SummaryAPIInterface summaryAPI, EpisodeDataAccess episodeDAO) {
        SummaryPresenter presenter = new SummaryPresenter(episodeViewModel);
        SummaryInteractor interactor = new SummaryInteractor(presenter, episodeDAO);
        return new SummaryController(interactor, summaryAPI);
    }
}
