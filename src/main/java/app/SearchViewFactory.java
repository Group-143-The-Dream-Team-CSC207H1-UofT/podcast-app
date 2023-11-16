package app;

import api.EmbeddingsInterface;
import data_access.EpisodeDataAccess;
import data_access.VectorDatabase;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import view.SearchView;


public class SearchViewFactory {

    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, EpisodeDataAccess episodeDAO, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings) {
        SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, episodeDAO, vectorDatabase, embeddings);
        return new SearchView(searchViewModel, searchController);
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, EpisodeDataAccess episodeDAO, VectorDatabase vectorDatabase, EmbeddingsInterface embeddings){
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel);
        SearchInputBoundary searchInteractor = new SearchInteractor(searchOutputBoundary, vectorDatabase, embeddings, episodeDAO);
        return new SearchController(searchInteractor);
    }
}
