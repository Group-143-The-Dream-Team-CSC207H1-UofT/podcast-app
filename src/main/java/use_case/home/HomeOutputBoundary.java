package use_case.home;

public interface HomeOutputBoundary {
    void prepareSuccessView(HomeOutputData outputData);
    void prepareFailView(HomeOutputData outputData);
}
