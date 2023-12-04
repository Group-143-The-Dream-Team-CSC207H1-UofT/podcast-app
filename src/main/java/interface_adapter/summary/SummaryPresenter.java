package interface_adapter.summary;

import interface_adapter.episode.EpisodeState;
import interface_adapter.episode.EpisodeViewModel;
import use_case.summary.SummaryOutputBoundary;
import use_case.summary.SummaryOutputData;

public class SummaryPresenter implements SummaryOutputBoundary {
    EpisodeViewModel viewModel;

    public SummaryPresenter(EpisodeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SummaryOutputData outputData) {
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SummaryOutputData outputData) {

    }
}
