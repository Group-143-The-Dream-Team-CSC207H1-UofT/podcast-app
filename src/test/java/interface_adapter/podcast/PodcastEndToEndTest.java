package interface_adapter.podcast;

import data_access.*;
import entities.MediaItem;
import entities.Podcast;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.podcast.PodcastController;
import interface_adapter.podcast.PodcastPresenter;
import interface_adapter.podcast.PodcastViewModel;
import org.junit.jupiter.api.Test;
import use_case.podcast.PodcastInputBoundary;
import use_case.podcast.PodcastInputData;
import use_case.podcast.PodcastInteractor;
import use_case.podcast.PodcastOutputBoundary;
import view.PodcastView;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class PodcastEndToEndTest {
    @Test
    public void PodcastEndToEndTest() {
        UUID id = UUID.randomUUID();
        Podcast podcast = new Podcast(id, "test", "test", null, null);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        CreateEpisodeViewModel createEpisodeViewModel = new CreateEpisodeViewModel();
        PodcastViewModel podcastViewModel = new PodcastViewModel();
        PodcastDataAccess podcastDataAccess = new PodcastDataAccess() {
            @Override
            public boolean savePodcast(Podcast podcast) {
                return false;
            }

            @Override
            public Podcast getPodcastById(UUID id) {
                return podcast;
            }

            @Override
            public List<MediaItem> getEpisodesForPodcast(UUID podcastId) {
                return null;
            }

            @Override
            public Collection<Podcast> getAllPodcasts() {
                return null;
            }
        };
        PodcastOutputBoundary presenter = new PodcastPresenter(podcastViewModel, createEpisodeViewModel, homeViewModel, viewManagerModel);
        PodcastInputBoundary interactor = new PodcastInteractor(presenter, podcastDataAccess);
        PodcastController controller = new PodcastController(interactor);
        controller.execute(id);
        assert viewManagerModel.getActiveView().equals(podcastViewModel.getViewName());
        assert podcastViewModel.getState().getCurrentPodcast().getName().equals("test");
        assert podcastViewModel.getState().getCurrentPodcast().getDescription().equals("test");
        assert podcastViewModel.getState().getErrorMessage().equals("");
    }
}
