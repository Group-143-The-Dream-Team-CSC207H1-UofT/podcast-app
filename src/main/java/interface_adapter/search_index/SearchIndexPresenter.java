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

    /**
     * Updates search index view model with the episode information from the output data.
     * @param outputData the output data from the input boundary.
     */
    @Override
    public void prepareSuccessView(SearchIndexOutputData outputData) {
        Episode episode = outputData.getEpisode();
        SearchIndexState state = viewModel.getState();
        state.setEpisode(episode);
        state.setErrorMessage(null);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    /**
     * Updates search index view model with no episode information under the presumption
     * that there is an error in this presenter.
     * @param error the error message.
     */
    @Override
    public void prepareFailView(String error) {
        SearchIndexState state = viewModel.getState();
        state.setEpisode(null);
        state.setErrorMessage(error);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
