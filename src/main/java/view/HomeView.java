package view;

import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.podcast.PodcastController;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class HomeView extends JFrame implements PropertyChangeListener {
    public JPanel panel;
    private JButton searchButton;
    private JButton createButton;
    private JPanel secondaryPanel;
    public final String viewName = "home";

    public HomeView(HomeController controller, PodcastController podcastController, HomeViewModel viewModel, ViewManagerModel viewManagerModel) {
        viewModel.addPropertyChangeListener(this);
        controller.execute();
        searchButton.addActionListener(e -> {
            viewManagerModel.setActiveView("search");
            viewManagerModel.firePropertyChanged();
        });
        createButton.addActionListener(e -> {
            viewManagerModel.setActiveView("create podcast");
            viewManagerModel.firePropertyChanged();
        });
        HomeState state = viewModel.getState();
        secondaryPanel.setLayout(new GridLayout(state.getAllPodcasts().size(), 1));
        List<Podcast> allPodcasts = state.getAllPodcasts();
        for (Podcast podcast: allPodcasts) {
            JButton podcastButton = new JButton(podcast.getName());
            System.out.println(podcast.getName());
            podcastButton.addActionListener(e -> {
                System.out.println("Go to podcast view...");
                podcastController.execute(podcast.getId());
            });
            secondaryPanel.add(podcastButton);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        HomeState state = (HomeState) evt.getNewValue();
//        // I'm not really sure what needs to happen here, this view will be mostly static, it should change when there
//        // are new podcasts uploaded only
//        secondaryPanel.removeAll();
//        List<Podcast> allPodcasts = state.getAllPodcasts();
//        for (Podcast podcast: allPodcasts) {
//            JButton podcastButton = new JButton(podcast.getName());
//            podcastButton.addActionListener(e -> System.out.println("Go to podcast view..."));
//            secondaryPanel.add(podcastButton);
//        }
    }
}
