package view;

import entities.Episode;
import entities.TextChunk;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.search.EpisodeSearchResult;
import use_case.search.SearchResult;
import use_case.search.TextChunkSearchResult;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView implements PropertyChangeListener {
    public final String viewName = "search";
    private JTextField searchQuery;
    private JButton searchButton;
    private JTextArea resultsTextArea;
    public JPanel panel;
    private final SearchViewModel searchViewModel;
    private final SearchController searchController;

    public SearchView(SearchViewModel searchViewModel, SearchController searchController) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);
        this.searchController = searchController;
        searchButton.addActionListener(e -> {
            this.searchController.execute(searchQuery.getText());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search")) {
            SearchState searchState = (SearchState) evt.getNewValue();

            // get the search results
            StringBuilder stringResults = new StringBuilder();
            for (SearchResult result : searchState.getSearchResults()) {
                Episode episode = result.getEpisode();
                if (result instanceof EpisodeSearchResult){
                    stringResults.append(String.format("%s: %s\n\n", episode.getTitle(), episode.getItemDescription()));
                } else if (result instanceof TextChunkSearchResult) {
                    TextChunk textChunk = ((TextChunkSearchResult) result).getTextChunk();
                    stringResults.append(String.format("%s: %d - %d\n%s\n\n",
                            episode.getTitle(), textChunk.getStart(), textChunk.getEnd(), textChunk.getText()));
                }
            }
            if (stringResults.length() == 0) {
                this.resultsTextArea.setText("No results found...");
            } else {
                this.resultsTextArea.setText(stringResults.toString());
            }
        }
    }
}
