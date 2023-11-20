package data_access;

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
        File podcastsCSV;
        try {
            podcastsCSV = new File(this.getClass().getResource("/podcasts.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading podcasts.csv.");
            e.printStackTrace();
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(podcastsCSV));
            reader.readLine();  // read the header row
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                UUID id = UUID.fromString(col[0]);
                String title = col[1];
//                User createdBy = new User(col[2]);

                // Todo: User

                List<MediaItem> podcastEpisodes = parseEpisodeIds(col[3]);
                Podcast podcast = new Podcast(id, title, null, podcastEpisodes);
                podcastMap.put(id, podcast);
            }
        } catch (IOException e) {
            System.out.println("Could not load podcasts from podcasts.csv.");
        }
    }

    private List<MediaItem> parseEpisodeIds(String episodeIdsString) {
        // Assuming episodeIdsString is "(1,2,3)"
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
            writer.write("id,title,author,(episodeId)\n");

            // Todo: Check if it already exists?

            for (Podcast podcast : podcastMap.values()) {
                // Get a list of episode IDs as strings.
                List<String> episodeIdsList = null;
                for (MediaItem episode : podcast.getItems()) {
                    UUID id = episode.getId();
                    episodeIdsList.add(id.toString());
                }
                // Line 86 formats the list of IDs as a String similar to the ones saved into podcasts.csv
                String episodeIds = episodeIdsList.stream().collect(Collectors.joining(",", "(", ")"));
                String podcastString = String.format("%s,%s,%s,%s",
                        podcast.getId().toString(), podcast.getName(), podcast.getCreatedBy(), episodeIds);
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

}




