package view;

import entities.Episode;
import entities.TextChunk;
import interface_adapter.display_episode.DisplayEpisodeState;
import interface_adapter.display_episode.DisplayEpisodeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EpisodeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "episode";
    private final DisplayEpisodeViewModel displayEpisodeViewModel;
    private JLabel titleLabel;
    private JTextArea descriptionTextArea;
    private JList<TextChunk> textChunksList;
    private final JButton seeSummaryButton;
    private final JTextArea summaryTextArea;
    private final JButton playButton;

    public EpisodeView(DisplayEpisodeViewModel displayEpisodeViewModel) {
        this.displayEpisodeViewModel = displayEpisodeViewModel;
        this.displayEpisodeViewModel.addPropertyChangeListener(this);

        titleLabel = new JLabel();
        descriptionTextArea = new JTextArea();
        textChunksList = new JList<>();
        seeSummaryButton = new JButton("See Summary");
        summaryTextArea = new JTextArea();
        playButton = new JButton("Play Episode");

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(descriptionTextArea), BorderLayout.CENTER);

        DefaultListModel<TextChunk> listModel = new DefaultListModel<>();
        textChunksList.setModel(listModel);
        add(new JScrollPane(textChunksList), BorderLayout.CENTER);

        seeSummaryButton.addActionListener(e -> toggleSummaryVisibility());
        add(seeSummaryButton, BorderLayout.SOUTH);

        summaryTextArea.setVisible(false);
        add(summaryTextArea, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(playButton);
        buttonPanel.add(seeSummaryButton);
    }

    public void displayEpisode(Episode episode) {
        titleLabel.setText(episode.getTitle());
        descriptionTextArea.setText(episode.getItemDescription());

        // Populate list with TextChunks
        DefaultListModel<TextChunk> listModel = (DefaultListModel<TextChunk>) textChunksList.getModel();
        listModel.clear();
        for (TextChunk chunk : displayEpisodeViewModel.getState().getTextChunks()) {
            listModel.addElement(chunk);
        }

        setListCellRendererWithHighlighting(textChunksList, displayEpisodeViewModel.getState().getCurrentTextChunkIndex());
        String summary = episode.getSummary();
        if (summary != null) {
            summaryTextArea.setText(summary);
            toggleSummaryVisibility();
        }
    }

    private void toggleSummaryVisibility() {
        summaryTextArea.setVisible(!summaryTextArea.isVisible());
    }

    private void setListCellRendererWithHighlighting(JList<TextChunk> list, int highlightedIndex) {
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                TextChunk chunk = (TextChunk) value;
                setText(chunk.toString());

                if (index == highlightedIndex) {
                    setForeground(Color.RED);
                } else {
                    setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                    setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                }

                return this;
            }
        });
    }


    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == playButton) {
            // Call controller for playing episode
        } else {
            // Handle other actions
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DisplayEpisodeState state = (DisplayEpisodeState) evt.getNewValue();
        displayEpisode(state.getCurrentEpisode());
    }
}
