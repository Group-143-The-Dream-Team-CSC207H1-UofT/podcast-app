package view;

import entities.Episode;
import entities.TextChunk;
import interface_adapter.ViewManagerModel;
import interface_adapter.episode.EpisodeState;
import interface_adapter.episode.EpisodeViewModel;
import interface_adapter.summary.SummaryController;
import use_case.summary.SummaryInputData;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EpisodeView implements PropertyChangeListener {
    public final String viewName = "episode";
    private final EpisodeViewModel viewModel;
    private JLabel titleLabel;
    private JList<TextChunk> textChunksList;
    private JButton backButton;
    public JPanel panel;
    private JTextPane descriptionTextPane;
    private JButton playPauseButton;
    private JScrollPane transcriptScrollPane;
    private JButton summaryButton;
    private Clip episodeAudioClip;
    private boolean audioPlaying;
    private Runnable updateTextChunkHighlighting;
    private SummaryController summaryController;
    private Episode currentEpisode;

    public EpisodeView(ViewManagerModel viewManagerModel, EpisodeViewModel viewModel, SummaryController summaryController) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.summaryController = summaryController;

        DefaultListModel<TextChunk> listModel = new DefaultListModel<>();
        textChunksList.setModel(listModel);

        backButton.addActionListener(e -> {
            episodeAudioClip.stop();
            viewManagerModel.setActiveView("podcast");
            viewManagerModel.firePropertyChanged();;
        });

        playPauseButton.addActionListener(event -> {
            if (audioPlaying) {
                episodeAudioClip.stop();
                playPauseButton.setText("▶");
                audioPlaying = false;
            } else {
                episodeAudioClip.start();
                playPauseButton.setText("⏸");
                audioPlaying = true;
            }
        });
        summaryButton.addActionListener(e -> displaySummary());
    }

    public void displayEpisode(Episode episode) {
        titleLabel.setText(episode.getTitle());
        descriptionTextPane.setText(episode.getItemDescription());

        // Populate list with TextChunks
        DefaultListModel<TextChunk> listModel = (DefaultListModel<TextChunk>) textChunksList.getModel();
        listModel.clear();
        for (TextChunk chunk : viewModel.getState().getTextChunks()) {
            listModel.addElement(chunk);
        }

//        summaryTextArea.setText(episode.getSummary());  // TODO add this

        File episodeAudioFile;
        try {
            episodeAudioFile = new File(this.getClass().getResource(String.format("/audioFiles/%s.wav", viewModel.getState().getCurrentEpisode().getId())).toURI());
        } catch (URISyntaxException e) {
            System.out.println("Failed to find audio file for episode.");
            return;
        }
        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(episodeAudioFile);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get Clip
        try {
            episodeAudioClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            episodeAudioClip.open(audioStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        episodeAudioClip.setMicrosecondPosition(viewModel.getState().getCurrentTextChunk().getStart() * 1000);
        audioPlaying = false;
        playPauseButton.setText("▶");

        updateTextChunkHighlighting = () -> {
            long currentTime = episodeAudioClip.getMicrosecondPosition() / 1000;
            for (TextChunk chunk : viewModel.getState().getCurrentEpisode().getTranscript().getTextChunks()) {
                if (chunk.getStart() <= currentTime && chunk.getEnd() >= currentTime) {
                    setListCellRendererWithHighlighting(textChunksList, chunk);
                    transcriptScrollPane.revalidate();
                    transcriptScrollPane.repaint();
                    return;
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(updateTextChunkHighlighting, 0, 250, TimeUnit.MILLISECONDS);
    }



    private void setListCellRendererWithHighlighting(JList<TextChunk> list, TextChunk highlightedChunk) {
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
                setText(chunk.getText());

                if (chunk == highlightedChunk) {
                    setForeground(Color.RED);
                } else {
                    setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                    setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                }

                return this;
            }
        });
    }

    private void displaySummary() {
        if (currentEpisode.getSummary() == null || currentEpisode.getSummary().isEmpty()) {
            SummaryInputData inputData = new SummaryInputData(currentEpisode);
            summaryController.execute(inputData);
        }
        JOptionPane.showMessageDialog(panel, "<html><body><p style='width: 250px;'>"+currentEpisode.getSummary()+"</p></body></html>");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EpisodeState state = (EpisodeState) evt.getNewValue();
        currentEpisode = state.getCurrentEpisode();
        displayEpisode(state.getCurrentEpisode());
    }
}
