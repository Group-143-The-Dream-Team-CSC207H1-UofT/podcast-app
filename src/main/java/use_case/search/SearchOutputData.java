package use_case.search;

import java.util.List;

public class SearchOutputData {
    private final List<SearchResult> searchResults;

    public SearchOutputData(List<SearchResult> results) {
        this.searchResults = results;
    }

    /**
     * @return the SearchResults contained in this object.
     */
    public List<SearchResult> getSearchResults() {
        return searchResults;
    }
}
