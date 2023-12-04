package app;

import data_access.EpisodeDataAccessObject;
import data_access.PodcastDataAccess;
import data_access.PodcastDataAccessObject;
import data_access.TranscriptDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Test;
import view.HomeView;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HomeViewFactoryTest {
    @Test
    public void TestHomeViewFactory() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        CreateEpisodeViewModel createEpisodeViewModel = new CreateEpisodeViewModel();
        PodcastDataAccess podcastDataAccess = new PodcastDataAccessObject(new EpisodeDataAccessObject(new TranscriptDataAccessObject()));
        HomeView view = HomeViewFactory.create(viewManagerModel, homeViewModel, podcastViewModel, createEpisodeViewModel, podcastDataAccess);
        assertNotNull(view);
        assert(view.viewName.equals("home"));
    }
}
