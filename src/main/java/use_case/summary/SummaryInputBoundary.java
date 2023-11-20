package use_case.summary;

import api.SummaryAPIInterface;

public interface SummaryInputBoundary {
    void execute(SummaryInputData inputData, SummaryAPIInterface APIWrapper);
}
