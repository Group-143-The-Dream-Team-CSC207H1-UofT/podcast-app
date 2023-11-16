package interface_adapter.search;

import use_case.search.SearchResult;

import java.util.List;

public class SearchState {
    private List<SearchResult> searchResults;

    public SearchState() {}

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    public List<SearchResult> getSearchResults() {
        return this.searchResults;
    }
}
