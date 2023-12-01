package app;

import data_access.PodcastDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_podcast.CreatePodcastController;
import interface_adapter.create_podcast.CreatePodcastPresenter;
import interface_adapter.create_podcast.CreatePodcastViewModel;
import interface_adapter.podcast.PodcastViewModel;
import use_case.create_podcast.CreatePodcastInputBoundary;
import use_case.create_podcast.CreatePodcastInteractor;
import view.CreatePodcastView;

public class CreatePodcastViewFactory {
    public static CreatePodcastView create(ViewManagerModel viewManagerModel, CreatePodcastViewModel createPodcastViewModel, PodcastViewModel podcastViewModel, PodcastDataAccess podcastDAO) {
        CreatePodcastPresenter presenter = new CreatePodcastPresenter(createPodcastViewModel, podcastViewModel, viewManagerModel);
        CreatePodcastInteractor interactor = new CreatePodcastInteractor(presenter, podcastDAO);
        CreatePodcastController controller = new CreatePodcastController(interactor);
        CreatePodcastView view = new CreatePodcastView(controller, createPodcastViewModel);
        return view;
    }
}
