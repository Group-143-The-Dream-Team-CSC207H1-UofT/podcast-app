package view;

import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;

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

    private final PodcastViewModel podcastViewModel;

    public PodcastView(PodcastViewModel podcastViewModel) {
        this.podcastViewModel = podcastViewModel;
        this.podcastViewModel.addPropertyChangeListener(this);
        setTitle("Podcast");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        PodcastState state = this.podcastViewModel.getState();
        podcastTextPane.setText(state.getPodcast().getName());



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Going to home screen");
            }
        });
    }

    private void addAllPodcastEpisodes(PodcastState state){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
