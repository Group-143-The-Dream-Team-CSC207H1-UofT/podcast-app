package view;

import entities.MediaItem;
import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeState;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.episode.EpisodeController;
import interface_adapter.home.HomeController;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.UUID;

public class PodcastView implements PropertyChangeListener {
    private final EpisodeController episodeController;
    public final String viewName = "podcast";
    private JTextPane descriptionTextPane;
    private JButton backButton;
    private JLabel titleLabel;
    private JPanel episodeList;
    public JPanel panel;
    private JButton createEpisodeButton;
    private UUID currentPodcastId;

    public PodcastView(ViewManagerModel viewManagerModel, PodcastViewModel podcastViewModel, CreateEpisodeViewModel createEpisodeViewModel, EpisodeController episodeController, HomeController homeController) {
        podcastViewModel.addPropertyChangeListener(this);
        this.episodeController = episodeController;
        backButton.addActionListener(e -> {
            homeController.execute();
            viewManagerModel.setActiveView("home");
            viewManagerModel.firePropertyChanged();
        });
        createEpisodeButton.addActionListener(e -> {
            CreateEpisodeState state = createEpisodeViewModel.getState();
            state.setCurrentPodcastId(currentPodcastId);
            createEpisodeViewModel.setState(state);
            viewManagerModel.setActiveView("upload");
            viewManagerModel.firePropertyChanged();
        });
        this.episodeList.setLayout(new BoxLayout(this.episodeList, BoxLayout.Y_AXIS));
    }

    private void displayPodcast(Podcast podcast) {
        currentPodcastId = podcast.getId();
        titleLabel.setText(podcast.getName());
        descriptionTextPane.setText(podcast.getDescription());
        updateEpisodeList(podcast.getItems());
    }

    private void updateEpisodeList(List<MediaItem> episodes) {
        episodeList.removeAll();  // Clear existing buttons

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
