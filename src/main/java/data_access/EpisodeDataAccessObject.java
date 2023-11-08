package data_access;

import entities.Episode;
import entities.MediaItem;
import entities.Podcast;
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

public class EpisodeDataAccessObject implements EpisodeDataAccess {

    private final Map<UUID, Episode> episodeMap;

    private final TranscriptDataAccess transcriptDAO;

    public EpisodeDataAccessObject(TranscriptDataAccess transcriptDAO, String filePath) {
        episodeMap = new HashMap<>();
        this.transcriptDAO = transcriptDAO;
        loadEpisodes(filePath);
    }

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

    @Override
    public boolean saveEpisode(Episode episode, String filePath) {
        episodeMap.put(episode.getId(), episode);
        return save(filePath);
    }

    @Override
    public Episode getEpisodeById(UUID id) {
        return episodeMap.get(id);
    }

    private boolean save(String filePath) {
        File episodesCSV;
        try {
            episodesCSV = new File(this.getClass().getResource(filePath).toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading episodes.csv.");
            e.printStackTrace();
            return false;
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(episodesCSV));
            writer.write("id,title,description,transcriptId,summary\n");
            String transcriptId = "";
            Transcript transcript;
            for (Episode episode : episodeMap.values()) {
                if ((transcript = episode.getTranscript()) != null) {
                    transcriptId = transcript.getId().toString();
                }
                String episodeString = String.format("%s,%s,%s,%s,%s",
                        episode.getId().toString(), episode.getTitle(), episode.getItemDescription(), transcriptId, episode.getSummary());
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

    private void loadEpisodes(String filePath) {
        File episodesCSV;
        try {
            episodesCSV = new File(this.getClass().getResource(filePath).toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading episodes.csv.");
            e.printStackTrace();
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(episodesCSV));
            reader.readLine();  // read the header row
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                UUID id = UUID.fromString(col[0]);
                String title = col[1];
                String description = col[2];
                String transcriptId = col[3];
                Transcript transcript = null;
                if (!transcriptId.isEmpty()) {
                    transcript = transcriptDAO.getTranscriptById(UUID.fromString(transcriptId));
                }
                String summary = col[4];
                Episode episode = new Episode(id, title, description, transcript, summary);
                episodeMap.put(id, episode);
            }
        } catch (IOException e) {
            System.out.println("Could not load episodes from episodes.csv.");
        }
    }

}
