package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.*;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel viewModel;
    private final ViewManagerModel viewManagerModel;  // TODO: remove if not needed

    public SearchPresenter(SearchViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData outputData) {
        SearchState currState = viewModel.getState();
        currState.setSearchResults(outputData.getSearchResults());
        viewModel.setState(currState);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO: fix this
        System.err.println(error);
    }
}
