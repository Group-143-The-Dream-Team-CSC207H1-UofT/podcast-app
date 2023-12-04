package interface_adapter.search_index;

import entities.Episode;
import use_case.search_index.SearchIndexInputBoundary;
import use_case.search_index.SearchIndexInputData;

public class SearchIndexController {
    private final SearchIndexInputBoundary searchIndexUseCaseInteractor;

    public SearchIndexController(SearchIndexInputBoundary searchIndexUseCaseInteractor) {
        this.searchIndexUseCaseInteractor = searchIndexUseCaseInteractor;
    }

    /**
     * Takes an episode and passes it to the interactor for searchIndex.
     * @param episode An episode object.
     */
    public void execute(Episode episode) {
        SearchIndexInputData inputData = new SearchIndexInputData(episode);
        searchIndexUseCaseInteractor.execute(inputData);
    }
}
