package view;

import entities.Episode;
import interface_adapter.upload.UploadController;
import interface_adapter.upload.UploadViewModel;
import interface_adapter.upload.UploadState;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class UploadView extends JPanel implements ActionListener, PropertyChangeListener {
    private final UploadViewModel uploadViewModel;
    private final UploadController uploadController;
    private final JLabel status;
    private final JButton uploadButton;

    public UploadView(UploadController uploadController, UploadViewModel uploadViewModel) {
        this.uploadController = uploadController;
        this.uploadViewModel = uploadViewModel;
        uploadViewModel.addPropertyChangeListener(this);

        status = new JLabel("Waiting for file selection.");
        uploadButton = new JButton("Upload Podcast");
        uploadButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(status);
        this.add(uploadButton);
    }
    // temporarily handling button click in actionPerformed
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(uploadButton)) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String selectedFileName = selectedFile.getName();
                String description = "temp description for now";
                uploadController.execute(selectedFileName, selectedFile.toURI(), description);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update the view based on the changes in the upload view model
        UploadState uploadState = (UploadState) evt.getNewValue();
        Episode episode = uploadState.getEpisode();
        String errorMessage = uploadState.getErrorMessage();
        if (episode != null) {
            status.setText(String.format("Successfully uploaded file for %s", episode.getTitle()));
        } else if (!errorMessage.isEmpty()) {
            status.setText(errorMessage);
        }
    }
}
