package interface_adapter.search_index;

import entities.Episode;
import interface_adapter.ViewManagerModel;
import use_case.search_index.SearchIndexOutputBoundary;
import use_case.search_index.SearchIndexOutputData;

public class SearchIndexPresenter implements SearchIndexOutputBoundary {
    private final SearchIndexViewModel viewModel;
    private final ViewManagerModel viewManagerModel;  // TODO: remove if not needed

    public SearchIndexPresenter(SearchIndexViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchIndexOutputData outputData) {
        Episode episode = outputData.getEpisode();
        SearchIndexState state = viewModel.getState();
        state.setEpisode(episode);
        state.setErrorMessage(null);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchIndexState state = viewModel.getState();
        state.setEpisode(null);
        state.setErrorMessage(error);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
