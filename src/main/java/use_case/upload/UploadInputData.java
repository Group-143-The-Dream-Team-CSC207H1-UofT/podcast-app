package use_case.upload;

import java.io.File;

public class UploadInputData {

    final private String title;
    final private String author;
    final private String audioFilePath;

    public UploadInputData(String title, String author, String audioFilePath) {
        this.title = title;
        this.author = author;
        this.audioFilePath = audioFilePath;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

}
