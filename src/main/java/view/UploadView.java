package view;

import entities.Episode;

import interface_adapter.transcribe.TranscribeController;
import interface_adapter.transcribe.TranscribeState;
import interface_adapter.transcribe.TranscribeViewModel;

import interface_adapter.upload.UploadController;
import interface_adapter.upload.UploadViewModel;
import interface_adapter.upload.UploadState;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URI;

public class UploadView extends JPanel implements PropertyChangeListener {
    private final UploadViewModel uploadViewModel;
    private final UploadController uploadController;
    private final JLabel status;
    final JTextField titleInputField = new JTextField(15);
    final JTextField descriptionInputField = new JTextField(15);
    private final JButton selectFileButton;
    private URI selectedFileURI;
    private final JButton submitButton;

    public UploadView(UploadController uploadController, UploadViewModel uploadViewModel) {
        this.uploadController = uploadController;
        this.uploadViewModel = uploadViewModel;
        uploadViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Episode Upload");
        status = new JLabel("Waiting for file selection.");
        selectFileButton = new JButton("No file selected");
        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                selectedFileURI = selectedFile.toURI();
                selectFileButton.setText(selectedFile.getName());
            }
        });
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            uploadController.execute(titleInputField.getText(), selectedFileURI, descriptionInputField.getText());
            titleInputField.setText("");
            descriptionInputField.setText("");
            selectedFileURI = null;
            selectFileButton.setText("No file selected");
        });

        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Title"), titleInputField);
        LabelTextPanel descriptionInfo = new LabelTextPanel(
                new JLabel("Description"), descriptionInputField);
        LabelButtonPanel fileInfo = new LabelButtonPanel(
                new JLabel("File"), selectFileButton);

        this.add(title);
        this.add(titleInfo);
        this.add(descriptionInfo);
        this.add(fileInfo);
        this.add(submitButton);
        this.add(status);
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

