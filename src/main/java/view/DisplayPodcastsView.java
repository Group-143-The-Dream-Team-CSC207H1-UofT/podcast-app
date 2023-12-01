package view;

import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_podcasts.DisplayPodcastsController;
import interface_adapter.display_podcasts.DisplayPodcastsState;
import interface_adapter.display_podcasts.DisplayPodcastsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//        setTitle("Home");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(600, 600);
//        setLocationRelativeTo(null);
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
            podcastButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Going to podcast view...");
                }
            });
            secondaryPanel.add(podcastButton);
        }
//        setContentPane(mainPanel);
//        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        DisplayPodcastsState state = (DisplayPodcastsState) evt.getNewValue();
//        // I'm not really sure what needs to happen here, this view will be mostly static, it should change when there
//        // are new podcasts uploaded only
//        secondaryPanel.removeAll();
//        List<Podcast> allPodcasts = state.getAllPodcasts();
//        for (Podcast podcast: allPodcasts) {
//            JButton podcastButton = new JButton(podcast.getName());
//            podcastButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.out.println("Go to podcast view...");
//                }
//            });
//            secondaryPanel.add(podcastButton);
//        }
    }
}
