package app;

import api.EmbeddingsInterface;
import data_access.EpisodeDataAccess;
import data_access.PodcastDataAccess;
import data_access.VectorDatabase;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeController;
import interface_adapter.episode.EpisodePresenter;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.podcast.PodcastViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.episode.EpisodeInputBoundary;
import use_case.episode.EpisodeInteractor;
import use_case.episode.EpisodeOutputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import view.SearchView;


public class SearchViewFactory {

    /**
     * Factory method to create and configure an instance of SearchView.
     * This method initializes instances of SearchController and EpisodeController for searching episode content
     * and displaying episodes.
     * @param viewManagerModel The model managing different views.
     * @param searchViewModel ViewModel for searching episodes.
     * @param episodeViewModel ViewModel for displaying episodes.
     * @param podcastViewModel ViewModel for displaying podcasts.
     * @param podcastDataAccessObject Data Access Object for reading and storing podcasts.
     * @param episodeDAO Data Access Object for reading and storing episodes.
     * @param vectorDatabase Vector database used for searching.
     * @param embeddings API used for vector embedding.
     * @return an instance of SearchView.
     */
    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, EpisodeViewModel episodeViewModel, PodcastViewModel podcastViewModel, PodcastDataAccess podcastDataAccessObject, EpisodeDataAccess episodeDAO, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings) {
        SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, episodeDAO, vectorDatabase, embeddings);
        EpisodeController episodeController = createDisplayEpisodeUseCase(viewManagerModel, podcastViewModel, episodeViewModel, episodeDAO, podcastDataAccessObject);
        return new SearchView(searchViewModel, searchController, episodeController, viewManagerModel);
    }

    private static EpisodeController createDisplayEpisodeUseCase(
            ViewManagerModel viewManagerModel,
            PodcastViewModel podcastViewModel,
            EpisodeViewModel episodeViewModel,
            EpisodeDataAccess episodeDataAccessObject,
            PodcastDataAccess podcastDataAccessObject
    ) {
        EpisodeOutputBoundary episodeOutputBoundary = new EpisodePresenter(viewManagerModel, episodeViewModel, podcastViewModel);
        EpisodeInputBoundary displayEpisodeInteractor = new EpisodeInteractor(episodeDataAccessObject, podcastDataAccessObject, episodeOutputBoundary);
        return new EpisodeController(displayEpisodeInteractor);
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, EpisodeDataAccess episodeDAO, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings){
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel);
        SearchInputBoundary searchInteractor = new SearchInteractor(searchOutputBoundary, vectorDatabase, embeddings, episodeDAO);
        return new SearchController(searchInteractor);
    }
}
