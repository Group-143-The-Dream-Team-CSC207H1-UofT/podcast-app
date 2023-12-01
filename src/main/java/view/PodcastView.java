package view;

import interface_adapter.display_podcast.DisplayPodcastState;
import interface_adapter.display_podcast.DisplayPodcastViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class PodcastView extends JFrame implements PropertyChangeListener {
    private JPanel initialPanel;
    private JButton backButton;
    private JPanel backButtonPanel;
    private JTextPane podcastTextPane;
    private JPanel podcastNamePanel;
    private JList episodeList;
    private JScrollPane episodeListScrollPanel;

    private final DisplayPodcastViewModel displayPodcastViewModel;

    public PodcastView(DisplayPodcastViewModel displayPodcastViewModel) {
        this.displayPodcastViewModel = displayPodcastViewModel;
        this.displayPodcastViewModel.addPropertyChangeListener(this);
        setTitle("Podcast");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        DisplayPodcastState state = this.displayPodcastViewModel.getState();
        podcastTextPane.setText(state.getPodcast().getName());



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Going to home screen");
            }
        });
    }

    private void addAllPodcastEpisodes(DisplayPodcastState state){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
