package data_access;

import entities.Episode;
import entities.Transcript;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 * Concrete implementation of EpisodeDataAccess for handling episode data.
 */
public class EpisodeDataAccessObject implements EpisodeDataAccess {

    private final Map<UUID, Episode> episodeMap;

    private final TranscriptDataAccess transcriptDAO;

    /**
     * Constructor for EpisodeDataAccessObject.
     *
     * @param transcriptDAO The data access object for transcript operations.
     */
    public EpisodeDataAccessObject(TranscriptDataAccess transcriptDAO) {
        episodeMap = new HashMap<>();
        this.transcriptDAO = transcriptDAO;
        loadEpisodes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveFile(URI fileLocation, UUID uniqueID) {
        // get the path of the source file
        Path srcPath = Paths.get(fileLocation);

        // get the URI of the destination directory
        URI destURI;
        try {
            destURI = new URI(this.getClass().getResource("/").toURI() + "/audioFiles/");
        } catch (URISyntaxException e) {
            System.out.println("URI syntax error.");
            e.printStackTrace();
            return false;
        }

        // create the directory doesn't already exist
        File destDir = new File(destURI);
        destDir.mkdir();

        // get the path of the destination directory
        Path destDirPath = Paths.get(destURI);
        try {
            Files.copy(srcPath, destDirPath.resolve(uniqueID.toString() + ".wav"));
            return true;
        } catch (IOException e) {
            System.out.println("Failed to save file.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getFileById(UUID id) {
        try {
            return new File(this.getClass().getResource("/audioFiles/" + id + ".wav").toURI());
        } catch (URISyntaxException e) {
            System.out.println(String.format("Error reading filepath for id %s", id.toString()));
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveEpisode(Episode episode) {
        episodeMap.put(episode.getId(), episode);
        return save();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Episode getEpisodeById(UUID id) {
        return episodeMap.get(id);
    }

    private boolean save() {
        File episodesCSV;
        try {
            episodesCSV = new File(this.getClass().getResource("/episodes.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading episodes.csv.");
            e.printStackTrace();
            return false;
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(episodesCSV));
            writer.write("id,title,description,transcriptId,podcastUUID,summary\n");
            String transcriptId = "";
            Transcript transcript;
            for (Episode episode : episodeMap.values()) {
                if ((transcript = episode.getTranscript()) != null) {
                    transcriptId = transcript.getId().toString();
                }
                String episodeString = String.format("%s,%s,%s,%s,%s,%s",
                        episode.getId().toString(),
                        formatStringForCSV(episode.getTitle()),
                        formatStringForCSV(episode.getItemDescription()),
                        transcriptId,
                        episode.getPodcastUUID().toString(),
                        formatStringForCSV(episode.getSummary()));
                writer.write(episodeString);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not save episodes to episodes.csv.");
            return false;
        }
    }

    private void loadEpisodes() {
        File episodesCSV;
        try {
            episodesCSV = new File(this.getClass().getResource("/episodes.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading episodes.csv.");
            e.printStackTrace();
            return;
        }
        try {
            CSVReader reader = new CSVReader(new FileReader(episodesCSV));
            List<String[]> lines = reader.readAll();
            lines.remove(0);  // remove the header row
            for (String[] row : lines) {
                UUID id = UUID.fromString(row[0]);
                String title = row[1];
                String description = row[2];
                String transcriptId = row[3];
                Transcript transcript = null;
                if (!transcriptId.isEmpty()) {
                    transcript = transcriptDAO.getTranscriptById(UUID.fromString(transcriptId));
                }
                String podcastUUID = row[4];
                String summary = row[5];
                Episode episode = new Episode(id, UUID.fromString(podcastUUID), title, description, transcript, summary);
                episodeMap.put(id, episode);
            }
        } catch (IOException e) {
            System.out.println("Could not load episodes from episodes.csv.");
        } catch (CsvException e) {
            System.out.println("Error in parsing episodes.csv");
        }
    }

    private String formatStringForCSV(String content) {
        if (content == null) {
            return "";
        }
        return String.format("\"%s\"", content.replace("\"", "\"\""));
    }
}
