package view;

import entities.Episode;
import entities.MediaItem;
import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeController;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PodcastView implements PropertyChangeListener {
    private final EpisodeController episodeController;
    public final String viewName = "podcast";
    private JTextPane descriptionTextPane;
    private JButton backButton;
    private JLabel titleLabel;
    private JList episodeList;
    public JPanel panel;

    public PodcastView(ViewManagerModel viewManagerModel, PodcastViewModel podcastViewModel, EpisodeController episodeController) {
        podcastViewModel.addPropertyChangeListener(this);
        this.episodeController = episodeController;
        backButton.addActionListener(e -> {
            viewManagerModel.setActiveView("home");
            viewManagerModel.firePropertyChanged();
        });
    }

    private void displayPodcast(Podcast podcast) {
        titleLabel.setText(podcast.getName());
        descriptionTextPane.setText(podcast.getDescription());
        updateEpisodeList(podcast.getItems());
    }

    private void updateEpisodeList(List<MediaItem> episodes) {
        episodeList.removeAll(); // Clear existing buttons

        for (MediaItem episode : episodes) {
            JButton episodeButton = new JButton(episode.getTitle());
            episodeButton.addActionListener(e -> episodeController.execute(episode.getId()));
            episodeList.add(episodeButton);
        }

        episodeList.revalidate();
        episodeList.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PodcastState state = (PodcastState) evt.getNewValue();
        displayPodcast(state.getCurrentPodcast());
    }

}
