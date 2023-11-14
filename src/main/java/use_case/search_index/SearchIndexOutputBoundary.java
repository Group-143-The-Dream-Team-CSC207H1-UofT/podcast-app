package use_case.search_index;

public interface SearchIndexOutputBoundary {
    void prepareSuccessView(SearchIndexOutputData outputData);
    void prepareFailView(String error);
}
