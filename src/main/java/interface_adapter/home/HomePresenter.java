package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomePresenter implements HomeOutputBoundary {
    private HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;
    public HomePresenter(HomeViewModel homeViewModel, ViewManagerModel viewManagerModel){
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Updates the HomeViewModel to display all saved podcasts.
     * @param outputData The output data from the input boundary.
     */
    @Override
    public void prepareSuccessView(HomeOutputData outputData) {
        HomeState currentState = homeViewModel.getState();
        currentState.setAllPodcasts(outputData.getAllPodcasts());
        homeViewModel.setState(currentState);
        homeViewModel.firePropertyChanged();
    }

    /**
     * Displays error retrieving podcasts.
     * @param outputData The output data from the input boundary.
     */
    @Override
    public void prepareFailView(HomeOutputData outputData) {
        HomeState currentState = new HomeState();
        currentState.setErrorMessage(outputData.getError());
        homeViewModel.setState(currentState);
        homeViewModel.firePropertyChanged();
    }
}
