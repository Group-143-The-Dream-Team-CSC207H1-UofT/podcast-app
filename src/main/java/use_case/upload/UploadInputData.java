package use_case.upload;

import java.io.File;

public class UploadInputData {

    final private String title;
    final private String description;
    final private String audioFilePath;

    public UploadInputData(String title, String description, String audioFilePath) {
        this.title = title;
        this.description = description;
        this.audioFilePath = audioFilePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }
}
