package view;

import entities.Episode;
import entities.MediaItem;
import entities.Podcast;
import interface_adapter.episode.EpisodeController;
import interface_adapter.display_podcast.DisplayPodcastState;
import interface_adapter.display_podcast.DisplayPodcastViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PodcastView extends JPanel implements PropertyChangeListener {

    private final DisplayPodcastViewModel displayPodcastViewModel;
    private final EpisodeController episodeController;
    public final String viewName="podcast";
    private JLabel podcastTitleLabel;
    private JTextArea podcastDescriptionTextArea;
    private JPanel episodePanel;
    private JButton backButton;

    public PodcastView(DisplayPodcastViewModel displayPodcastViewModel, EpisodeController episodeController) {
        this.displayPodcastViewModel = displayPodcastViewModel;
        this.displayPodcastViewModel.addPropertyChangeListener(this);
        this.episodeController = episodeController;

        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        podcastTitleLabel = new JLabel();
        episodePanel = new JPanel();
        episodePanel.setLayout(new BoxLayout(episodePanel, BoxLayout.Y_AXIS));
        backButton = new JButton("Back");
        backButton.addActionListener(e -> System.out.println("Go to home page..."));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(podcastTitleLabel, BorderLayout.NORTH);
        add(new JScrollPane(episodePanel), BorderLayout.EAST);
        add(backButton, BorderLayout.SOUTH);
    }

    private void displayPodcast(Podcast podcast) {
        podcastTitleLabel.setText(podcast.getName());
        updateEpisodeList(podcast.getItems());
    }

    private void updateEpisodeList(List<MediaItem> episodes) {
        episodePanel.removeAll(); // Clear existing buttons

        for (MediaItem episode : episodes) {
            JButton episodeButton = new JButton(episode.getTitle());
            episodeButton.addActionListener(e -> episodeController.execute(episode.getId()));
            episodePanel.add(episodeButton);
        }

        episodePanel.revalidate();
        episodePanel.repaint();
    }

    private void playEpisode(Episode episode) {
        // Logic to play the selected episode
        System.out.println("Playing episode: " + episode.getTitle());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DisplayPodcastState state = (DisplayPodcastState) evt.getNewValue();
        displayPodcast(state.getCurrentPodcast());
    }

}
