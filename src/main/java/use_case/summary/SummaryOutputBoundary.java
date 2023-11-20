package use_case.summary;

import java.io.IOException;

public interface SummaryOutputBoundary {
    void prepareSuccessView(SummaryOutputData outputData);
    void prepareFailView(SummaryOutputData outputData);
}
