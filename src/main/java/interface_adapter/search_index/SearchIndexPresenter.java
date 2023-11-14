package interface_adapter.search_index;

import use_case.search_index.SearchIndexOutputBoundary;
import use_case.search_index.SearchIndexOutputData;

public class SearchIndexPresenter implements SearchIndexOutputBoundary {
    @Override
    public void prepareSuccessView(SearchIndexOutputData outputData) {
        System.out.println("Succeeded in indexing transcript!");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
