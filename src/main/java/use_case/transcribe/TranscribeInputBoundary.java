package use_case.transcribe;

import java.io.IOException;

public interface TranscribeInputBoundary {
    void execute(TranscribeInputData inputdata);
}
