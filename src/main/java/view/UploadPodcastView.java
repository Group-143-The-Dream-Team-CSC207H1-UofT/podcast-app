package view;

import entities.Podcast;
import interface_adapter.display_podcast.DisplayPodcastController;
import interface_adapter.display_podcast.DisplayPodcastState;
import interface_adapter.display_podcast.DisplayPodcastViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UploadPodcastView extends JPanel implements PropertyChangeListener {
    public final String viewName = "upload_podcast";
    private final DisplayPodcastViewModel viewModel;
    private final DisplayPodcastController controller;
    private final JTextField titleInputField = new JTextField(15);
    private final JTextField descriptionInputField = new JTextField(15);
    private final JLabel status;
    private final JButton submitButton;
    public UploadPodcastView(DisplayPodcastController controller, DisplayPodcastViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Podcast Upload");
        status = new JLabel("Waiting for podcast submission.");
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
//            this.controller.execute(titleInputField.getText(), descriptionInputField.getText(), null, null);
            titleInputField.setText("");
            descriptionInputField.setText("");
        });

        LabelTextPanel titleInfo = new LabelTextPanel(
                new JLabel("Title"), titleInputField);
        LabelTextPanel descriptionInfo = new LabelTextPanel(
                new JLabel("Description"), descriptionInputField);

        this.add(title);
        this.add(titleInfo);
        this.add(descriptionInfo);
        this.add(submitButton);
        this.add(status);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DisplayPodcastState displayPodcastState = (DisplayPodcastState) evt.getNewValue();
        Podcast podcast = displayPodcastState.getCurrentPodcast();
        String errorMessage = displayPodcastState.getErrorMessage();
        if (podcast != null) {
            status.setText("Successfully uploaded podcast!");
            System.out.println("Switch to either home page or display podcast view.");
        } else if (!errorMessage.isEmpty()) {
            status.setText(errorMessage);
        }
    }
}
