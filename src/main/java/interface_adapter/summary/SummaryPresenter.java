package interface_adapter.summary;

import interface_adapter.display_episode.DisplayEpisodeState;
import interface_adapter.display_episode.DisplayEpisodeViewModel;
import use_case.summary.SummaryOutputBoundary;
import use_case.summary.SummaryOutputData;

public class SummaryPresenter implements SummaryOutputBoundary {
    DisplayEpisodeViewModel viewModel;
    @Override
    public void prepareSuccessView(SummaryOutputData outputData) {
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SummaryOutputData outputData) {

    }
}
