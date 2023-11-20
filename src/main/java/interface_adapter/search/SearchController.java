package interface_adapter.search;

import use_case.search.SearchInputBoundary;

public class SearchController {
    private final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }
    public void execute(String query) {
        searchUseCaseInteractor.execute(query);
    }
}
