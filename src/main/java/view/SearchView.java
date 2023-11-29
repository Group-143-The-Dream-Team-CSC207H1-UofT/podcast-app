package view;

import entities.Episode;
import entities.TextChunk;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import org.jetbrains.annotations.NotNull;
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
    public JPanel panel;
    private JPanel resultsPanel;
    private JButton homeButton;
    private final SearchViewModel searchViewModel;
    private final SearchController searchController;

    public SearchView(SearchViewModel searchViewModel, SearchController searchController, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);
        this.searchController = searchController;
        searchButton.addActionListener(e -> {
            this.searchController.execute(searchQuery.getText());
        });
        homeButton.addActionListener(e -> {
            viewManagerModel.setActiveView("home");
            viewManagerModel.firePropertyChanged();
        });
        this.resultsPanel.setLayout(new BoxLayout(this.resultsPanel, BoxLayout.Y_AXIS));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search")) {
            SearchState searchState = (SearchState) evt.getNewValue();

            // remove components from the panel
            this.resultsPanel.removeAll();

            // get the search results
            for (SearchResult result : searchState.getSearchResults()) {
                JButton button = getResultButton(result);
                this.resultsPanel.add(button);
            }

            if (this.resultsPanel.getComponentCount() == 0) {
                this.resultsPanel.add(new JLabel("No results found..."));
            }

            this.resultsPanel.revalidate();
            this.resultsPanel.repaint();
        }
    }

    @NotNull
    private static JButton getResultButton(SearchResult result) {
        JButton button = new JButton();
        Episode episode = result.getEpisode();

        if (result instanceof EpisodeSearchResult){
            button.setText(String.format("%s: %s\n\n", episode.getTitle(), episode.getItemDescription()));
            button.addActionListener(e -> {
                System.out.println(String.format("Go to episode %s", episode.getId()));
            });
        } else if (result instanceof TextChunkSearchResult) {
            TextChunk textChunk = ((TextChunkSearchResult) result).getTextChunk();
            button.setText(String.format("%s at %d - %d: %s",
                    episode.getTitle(), textChunk.getStart(), textChunk.getEnd(), textChunk.getText()));
            button.addActionListener(e -> {
                System.out.println(String.format("Go to episode %s, text chunk %d - %d", episode.getId(), textChunk.getStart(), textChunk.getEnd()));
            });
        }

        return button;
    }
}
