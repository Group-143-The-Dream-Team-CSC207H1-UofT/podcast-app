package view;

import interface_adapter.upload.UploadController;
import interface_adapter.upload.UploadViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class UploadView extends JPanel implements ActionListener, PropertyChangeListener {
    private final UploadViewModel uploadViewModel;
    private final UploadController uploadController;
    private final JButton uploadButton;

    public UploadView(UploadController uploadController, UploadViewModel uploadViewModel) {
        this.uploadController = uploadController;
        this.uploadViewModel = uploadViewModel;
        uploadViewModel.addPropertyChangeListener(this);


        uploadButton = new JButton("Upload Podcast");
        uploadButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
                uploadController.execute(fileChooser.getSelectedFile().getAbsolutePath(), selectedFileName);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update the view based on the changes in the upload view model
    }
}
