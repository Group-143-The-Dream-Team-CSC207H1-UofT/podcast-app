package view;

import entities.Episode;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_index.SearchIndexController;
import interface_adapter.search_index.SearchIndexState;
import interface_adapter.search_index.SearchIndexViewModel;
import interface_adapter.transcribe.TranscribeController;
import interface_adapter.transcribe.TranscribeState;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.create_episode.CreateEpisodeController;
import interface_adapter.create_episode.CreateEpisodeViewModel;
import interface_adapter.create_episode.CreateEpisodeState;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URI;

public class CreateEpisodeView extends JPanel implements PropertyChangeListener {
    public final String viewName = "upload";
    private final ViewManagerModel viewManagerModel;
    private final CreateEpisodeViewModel createEpisodeViewModel;
    private final TranscribeViewModel transcribeViewModel;
    private final SearchIndexViewModel searchIndexViewModel;
    private final CreateEpisodeController createEpisodeController;
    private  final  TranscribeController transcribeController;
    private final SearchIndexController searchIndexController;
    private final JLabel status;
    final JTextField titleInputField = new JTextField(15);
    final JTextField descriptionInputField = new JTextField(15);
    private final JButton selectFileButton;
    private URI selectedFileURI;
    private final JButton submitButton;

    public CreateEpisodeView(ViewManagerModel viewManagerModel, CreateEpisodeController createEpisodeController, CreateEpisodeViewModel createEpisodeViewModel, TranscribeViewModel transcribeViewModel, TranscribeController transcribeController, SearchIndexViewModel searchIndexViewModel, SearchIndexController searchIndexController) {
        this.viewManagerModel = viewManagerModel;
        this.createEpisodeController = createEpisodeController;
        this.createEpisodeViewModel = createEpisodeViewModel;
        this.createEpisodeViewModel.addPropertyChangeListener(this);
        this.transcribeController = transcribeController;
        this.transcribeViewModel = transcribeViewModel;
        this.transcribeViewModel.addPropertyChangeListener(this);
        this.searchIndexController = searchIndexController;
        this.searchIndexViewModel = searchIndexViewModel;
        this.searchIndexViewModel.addPropertyChangeListener(this);


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
            this.createEpisodeController.execute(titleInputField.getText(), selectedFileURI, descriptionInputField.getText(), createEpisodeViewModel.getState().getCurrentPodcast().getId());
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
        if (evt.getPropertyName().equals("upload")) {
            // Update the view based on the changes in the upload view model
            CreateEpisodeState createEpisodeState = (CreateEpisodeState) evt.getNewValue();
            Episode episode = createEpisodeState.getEpisode();
            String errorMessage = createEpisodeState.getErrorMessage();
            if (episode != null) {
                status.setText(String.format("Successfully uploaded file for %s", episode.getTitle()));
                transcribeController.execute(episode);

            } else if (!errorMessage.isEmpty()) {
                status.setText(errorMessage);
            }
        } else if (evt.getPropertyName().equals("transcribe")) {
            // we need to index the transcription using the searchIndex use case
            TranscribeState transcribeState = (TranscribeState) evt.getNewValue();
            Episode episode = transcribeState.getEpisode();
            String errorMessage = transcribeState.getErrorMessage();
            if (episode != null) {
                status.setText(String.format("Successfully uploaded & transcribed file for %s", episode.getTitle()));
                searchIndexController.execute(episode);
            } else if (!errorMessage.isEmpty()) {
                status.setText(errorMessage);
            }
        } else if (evt.getPropertyName().equals("search index")) {
            // we need to index the transcription using the searchIndex use case
            SearchIndexState searchIndexState = (SearchIndexState) evt.getNewValue();
            Episode episode = searchIndexState.getEpisode();
            String errorMessage = searchIndexState.getErrorMessage();
            if (episode != null) {
                System.out.println("Go back to podcast view");
                viewManagerModel.setActiveView("podcast");
                viewManagerModel.firePropertyChanged();
            } else if (!errorMessage.isEmpty()) {
                status.setText(errorMessage);
            }
        }
    }
}

