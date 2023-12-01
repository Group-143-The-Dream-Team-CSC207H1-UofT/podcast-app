package view;

import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcast.DisplayPodcastController;
import interface_adapter.display_podcasts.DisplayPodcastsController;
import interface_adapter.display_podcasts.DisplayPodcastsState;
import interface_adapter.display_podcasts.DisplayPodcastsViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class DisplayPodcastsView extends JFrame implements PropertyChangeListener {
    public JPanel mainPanel;
    private JButton searchButton;
    private JButton createButton;
    private JPanel secondaryPanel;
    private final DisplayPodcastsController controller;
    private final DisplayPodcastsViewModel viewModel;
    public final String viewName = "home";

    public DisplayPodcastsView(DisplayPodcastsController controller, DisplayPodcastsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        controller.execute();
        searchButton.addActionListener(e -> {
            viewManagerModel.setActiveView("search");
            viewManagerModel.firePropertyChanged();
        });
        createButton.addActionListener(e -> {
            viewManagerModel.setActiveView("upload_podcast");
            viewManagerModel.firePropertyChanged();
        });
        DisplayPodcastsState state = this.viewModel.getState();
        secondaryPanel.setLayout(new GridLayout(state.getAllPodcasts().size(), 1));
        List<Podcast> allPodcasts = state.getAllPodcasts();
        for (Podcast podcast: allPodcasts) {
            JButton podcastButton = new JButton(podcast.getName());
            System.out.println(podcast.getName());
            podcastButton.addActionListener(e -> {
                System.out.println("Go to podcast view...");
                displayPodcastController.execute(podcast.getId());
            });
            secondaryPanel.add(podcastButton);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DisplayPodcastsState state = (DisplayPodcastsState) evt.getNewValue();
        // I'm not really sure what needs to happen here, this view will be mostly static, it should change when there
        // are new podcasts uploaded only
        secondaryPanel.removeAll();
        List<Podcast> allPodcasts = state.getAllPodcasts();
        for (Podcast podcast: allPodcasts) {
            JButton podcastButton = new JButton(podcast.getName());
            podcastButton.addActionListener(e -> System.out.println("Go to podcast view..."));
            secondaryPanel.add(podcastButton);
        }
    }
}
