package app;

import javax.swing.JFrame;
import data_access.EpisodeDataAccess;
import data_access.EpisodeDataAccessObject;
import data_access.TranscriptDataAccess;
import data_access.TranscriptDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.upload.*;
import view.*;

public class Main {

    public static void main(String[] args) {
        TranscriptDataAccess transcriptDataAccessObject = new TranscriptDataAccessObject();
        EpisodeDataAccess episodeDataAccessObject = new EpisodeDataAccessObject(transcriptDataAccessObject);
        UploadViewModel uploadViewModel = new UploadViewModel();
        TranscribeViewModel transcribeViewModel = new TranscribeViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        UploadView uploadView = UploadUseCaseFactory.create(viewManagerModel, uploadViewModel, transcribeViewModel, episodeDataAccessObject, transcriptDataAccessObject);

        // Set up the main window (a JFrame)
        JFrame frame = new JFrame("Upload Podcast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(uploadView);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
