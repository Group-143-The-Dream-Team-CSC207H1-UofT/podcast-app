package data_access;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import entities.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class PodcastDataAccessObject implements PodcastDataAccess {

    private final Map<UUID, Podcast> podcastMap;
    private final EpisodeDataAccess episodeDAO;

    public PodcastDataAccessObject(EpisodeDataAccess episodeDAO) {
        podcastMap = new HashMap<>();
        this.episodeDAO = episodeDAO;
        loadPodcasts();
    }

    @Override
    public List<MediaItem> getEpisodesForPodcast(UUID podcastId) {
        Podcast podcast = podcastMap.get(podcastId);
        return podcast.getItems();
    }

    @Override
    public Collection<Podcast> getAllPodcasts() {
        return podcastMap.values();
    }

    @Override
    public Podcast getPodcastById(UUID id) {
        return podcastMap.get(id);
    }

    @Override
    public boolean savePodcast(Podcast podcast) {
        podcastMap.put(podcast.getId(), podcast);
        return save();
    }

    private void loadPodcasts() {
//        File podcastsCSV;
//        try {
//            podcastsCSV = new File(this.getClass().getResource("/podcasts.csv").toURI());
//        } catch (URISyntaxException e) {
//            System.out.println("Error reading podcasts.csv.");
//            e.printStackTrace();
//            return;
//        }
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(podcastsCSV));
//            reader.readLine();  // read the header row
//            String row;
//            while ((row = reader.readLine()) != null) {
//                String[] col = row.split(",");
//                UUID id = UUID.fromString(col[0]);
//                String title = col[1];
////                User createdBy = new User(col[2]);
//
//                // Todo: User
//                List<MediaItem> podcastEpisodes = parseEpisodeIds(col[3]);
//
//                Podcast podcast = new Podcast(id, title, null, podcastEpisodes);
//                podcastMap.put(id, podcast);
//            }
//        } catch (IOException e) {
//            System.out.println("Could not load podcasts from podcasts.csv.");
//        }
        File podcastsCSV;
        try {
            podcastsCSV = new File(this.getClass().getResource("/podcasts.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading podcasts.csv.");
            e.printStackTrace();
            return;
        }
        try {
            CSVReader reader = new CSVReader(new FileReader(podcastsCSV));
            List<String[]> lines = reader.readAll();
            lines.remove(0);  // remove the header row
            for (String[] row : lines) {
                UUID id = UUID.fromString(row[0]);
                String title = row[1];
                String description = row[2];
                List<MediaItem> podcastEpisodes = parseEpisodeIds(row[4]);
                Podcast podcast = new Podcast(id, title, description, null, podcastEpisodes);
                podcastMap.put(id, podcast);
            }
        } catch (IOException e) {
            System.out.println("Could not load episodes from podcasts.csv.");
        } catch (CsvException e) {
            System.out.println("Error in parsing podcasts.csv");
        }
    }

    private List<MediaItem> parseEpisodeIds(String episodeIdsString) {
        // Assuming episodeIdsString is "(1,2,3)"
        if (!Objects.equals(episodeIdsString, "()")) {
            String[] episodeIdsArray = episodeIdsString
                    .replace("(", "")
                    .replace(")", "")
                    .split(",");
            List<String> episodeIdList = Arrays.asList(episodeIdsArray);
            List<MediaItem> podcastEpisodes = null;
            for (String id : episodeIdList) {
                Episode episode = episodeDAO.getEpisodeById(UUID.fromString(id));
                podcastEpisodes.add(episode);
            }
            return podcastEpisodes;
        } else {
            return null;
        }
    }

    private boolean save() {
        File podcastCSV;
        try {
            podcastCSV = new File(this.getClass().getResource("/podcasts.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading podcasts.csv.");
            e.printStackTrace();
            return false;
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(podcastCSV));
            writer.write("id,title,description,author,(episodeId)\n");
            for (Podcast podcast : podcastMap.values()) {
                // Get a list of episode IDs as strings.
                List<String> episodeIdsList = null;
                if (podcast.getItems() != null) {
                    for (MediaItem episode : podcast.getItems()) {
                        UUID id = episode.getId();
                        episodeIdsList.add(id.toString());
                    }
                }
                String episodeIds;
                if (!podcast.getItems().isEmpty()) {
                    for (MediaItem episode : podcast.getItems()) {
                        UUID id = episode.getId();
                        episodeIdsList.add(id.toString());
                    }
                    // Line 86 formats the list of IDs as a String similar to the ones saved into podcasts.csv
                    episodeIds = episodeIdsList.stream().collect(Collectors.joining(",", "(", ")"));
                } else {
                    episodeIds = "()";
                }
                String podcastString = String.format("%s,%s,%s,%s,%s",
                        podcast.getId().toString(), formatStringForCSV(podcast.getName()), formatStringForCSV(podcast.getDescription()), podcast.getAssignedTo(), formatStringForCSV(episodeIds));
                writer.write(podcastString);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not save podcast to podcasts.csv.");
            return false;
        }
    }
    private String formatStringForCSV(String content) {
        if (content == null) {
            return null;
        }
        return String.format("\"%s\"", content.replace("\"", "\"\""));
    }

}




