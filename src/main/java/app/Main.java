package app;

import javax.swing.JFrame;
import interface_adapter.upload.*;
import use_case.upload.*;
import view.*;
public class Main {

    public static void main(String[] args) {
            UploadViewModel uploadViewModel = new UploadViewModel();
            UploadInputBoundary uploadUseCaseInteractor = new UploadInteractor();
            UploadController uploadController = new UploadController(uploadUseCaseInteractor);

            UploadView uploadView = new UploadView(uploadController, uploadViewModel);

            // Set up the main window (a JFrame)
            JFrame frame = new JFrame("Upload Podcast");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(uploadView);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        ;
    }
}

