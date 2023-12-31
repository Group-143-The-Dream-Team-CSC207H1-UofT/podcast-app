package view;

import entities.Podcast;
import interface_adapter.create_podcast.CreatePodcastController;
import interface_adapter.create_podcast.CreatePodcastViewModel;
import interface_adapter.podcast.PodcastController;
import interface_adapter.podcast.PodcastState;
import interface_adapter.podcast.PodcastViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreatePodcastView extends JPanel implements PropertyChangeListener {
    public final String viewName = "create podcast";
    final JTextField titleInputField = new JTextField(15);
    final JTextField descriptionInputField = new JTextField(15);
    private final JLabel status;
    private final JButton submitButton;
    public CreatePodcastView(CreatePodcastController controller, CreatePodcastViewModel viewModel) {
        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Create Podcast");
        status = new JLabel("Waiting for podcast submission.");
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            controller.execute(titleInputField.getText(), descriptionInputField.getText());
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
//        PodcastState podcastState = (PodcastState) evt.getNewValue();
//        Podcast podcast = podcastState.getCurrentPodcast();
//        String errorMessage = podcastState.getErrorMessage();
//        if (podcast != null) {
//            status.setText("Successfully uploaded podcast!");
//            System.out.println("Switch to either home page or display podcast view.");
//        } else if (!errorMessage.isEmpty()) {
//            status.setText(errorMessage);
//        }
    }
}
