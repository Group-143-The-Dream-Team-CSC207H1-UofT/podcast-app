package interface_adapter.search;

import use_case.search.SearchInputBoundary;

public class SearchController {
    private final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    /**
     * Takes a search query and passes it to the input boundary to display search results from episodes.
     * @param query The search query.
     */
    public void execute(String query) {
        searchUseCaseInteractor.execute(query);
    }
}
