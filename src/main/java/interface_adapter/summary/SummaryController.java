package interface_adapter.summary;

import api.SummaryAPIInterface;
import use_case.summary.SummaryInputBoundary;
import use_case.summary.SummaryInputData;

public class SummaryController {
    private final SummaryInputBoundary summaryInteractor;
    private final SummaryAPIInterface apiWrapper;
    public SummaryController(SummaryInputBoundary summaryInteractor, SummaryAPIInterface apiWrapper) {
        this.summaryInteractor = summaryInteractor;
        this.apiWrapper = apiWrapper;
    }


    /**
     * Generates a summary for the given Episode.
     * @param inputData contains the episode to generate a summary for
     */
    public void execute(SummaryInputData inputData) {
        summaryInteractor.execute(inputData, apiWrapper);
    }
}
