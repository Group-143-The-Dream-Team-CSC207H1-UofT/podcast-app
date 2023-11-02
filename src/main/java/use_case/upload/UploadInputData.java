package use_case.upload;

import java.io.File;

public class UploadInputData {

    final private String title;
    final private String audioFilePath;

    public UploadInputData(String title, String audioFilePath) {
        this.title = title;
        this.audioFilePath = audioFilePath;
    }

    public String getTitle() {
        return title;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

}
