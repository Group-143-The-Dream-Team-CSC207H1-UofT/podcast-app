package view;

import entities.Podcast;
import interface_adapter.display_podcast.DisplayPodcastController;
import interface_adapter.display_podcasts.DisplayPodcastsController;
import interface_adapter.display_podcasts.DisplayPodcastsState;
import interface_adapter.display_podcasts.DisplayPodcastsViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class DisplayPodcastsView implements PropertyChangeListener {

    public final String viewName = "podcasts";
    public JPanel panel;
    private JButton searchButton;
    private JButton createButton;
    private JPanel secondaryPanel;

    public DisplayPodcastsView(
        DisplayPodcastsController displayPodcastsController,
        DisplayPodcastController displayPodcastController,
        DisplayPodcastsViewModel viewModel
    ) {
        viewModel.addPropertyChangeListener(this);

        displayPodcastsController.execute();
        searchButton.addActionListener(e -> System.out.println("Going to search view..."));
        createButton.addActionListener(e -> System.out.println("Going to podcast view..."));
        DisplayPodcastsState state = viewModel.getState();
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
