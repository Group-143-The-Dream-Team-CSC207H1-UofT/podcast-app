package api;

import java.io.File;
import java.io.IOException;

public interface TranscriptionInterface {
    String transcribeFile(File file) throws IOException;
}
