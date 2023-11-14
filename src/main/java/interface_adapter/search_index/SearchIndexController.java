package interface_adapter.search_index;

import entities.Episode;
import use_case.search_index.SearchIndexInputBoundary;
import use_case.search_index.SearchIndexInputData;

public class SearchIndexController {
    private final SearchIndexInputBoundary searchIndexUseCaseInteractor;

    public SearchIndexController(SearchIndexInputBoundary searchIndexUseCaseInteractor) {
        this.searchIndexUseCaseInteractor = searchIndexUseCaseInteractor;
    }
    public void execute(Episode episode) {
        SearchIndexInputData inputData = new SearchIndexInputData(episode);
        searchIndexUseCaseInteractor.execute(inputData);
    }
}
