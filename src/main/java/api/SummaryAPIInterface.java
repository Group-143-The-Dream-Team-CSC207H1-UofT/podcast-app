package api;

import entities.Transcript;

import java.io.IOException;

public interface SummaryAPIInterface {

    String generateSummary(Transcript transcript) throws IOException;
}
