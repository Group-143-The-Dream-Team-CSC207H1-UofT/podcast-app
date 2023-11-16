package use_case.search;

import java.util.List;

public class SearchOutputData {
    private final List<SearchResult> searchResults;
    private final boolean useCaseFailed;

    public SearchOutputData(List<SearchResult> results, boolean failed) {
        this.searchResults = results;
        this.useCaseFailed = failed;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
