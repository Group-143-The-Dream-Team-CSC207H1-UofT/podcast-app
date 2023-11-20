package data_access;

import entities.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class UserDataAccessObject implements UserDataAccess{

    private final Map<UUID, User> userMap;
    private final PodcastDataAccess podcastDAO;

    public UserDataAccessObject(PodcastDataAccess podcastDAO) {
        userMap = new HashMap<>();
        this.podcastDAO = podcastDAO;
        loadUsers();
    }

    private void loadUsers(){
        File usersCSV;
        try {
            usersCSV = new File(this.getClass().getResource("/users.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading users.csv.");
            e.printStackTrace();
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersCSV));
            reader.readLine();  // read the header row
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                UUID id = UUID.fromString(col[0]);
                String username = col[1];
                List<MediaCollection> userPodcasts = parsePodcastIds(col[2]);
                User user = new User(id, username, userPodcasts);
                userMap.put(id, user);
            }
        } catch (IOException e) {
            System.out.println("Could not load users from users.csv.");
        }
    }

    private List<MediaCollection> parsePodcastIds(String podcastIdsString) {
        // Assuming episodeIdsString is "(1,2,3)"
        String[] podcastIdsArray = podcastIdsString
                .replace("(", "")
                .replace(")", "")
                .split(",");
        List<String> podcastIdList = Arrays.asList(podcastIdsArray);
        List<MediaCollection> podcastEpisodes = new ArrayList<>();
        for (String id : podcastIdList) {
            Podcast podcast = podcastDAO.getPodcastById(UUID.fromString(id));
            podcastEpisodes.add(podcast);
        }
        return podcastEpisodes;
    }

    private boolean save() {
        File usersCSV;
        try {
            usersCSV = new File(this.getClass().getResource("/users.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading users.csv.");
            e.printStackTrace();
            return false;
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(usersCSV));
            writer.write("id,username,(podcastId)\n");

            for (User user: userMap.values()){
                // Get a list of podcast IDs as strings.
                List<String> podcastIdsList = null;
                for (MediaCollection podcast: user.getUserPodcasts()) {
                    UUID id = podcast.getId();
                    podcastIdsList.add(id.toString());
                }
                String podcastIds = podcastIdsList.stream().collect(Collectors.joining(",", "(", ")"));
                String userString = String.format("%s,%s,%s",
                        user.getId().toString(), user.getUsername(), podcastIds);
                writer.write(userString);
                writer.newLine();
            }
            writer.close();
            return true;
        }catch (IOException e) {
            System.out.println("Could not save user to users.csv.");
            return false;
        }
    }

    @Override
    public boolean saveUser(User user) {
        userMap.put(user.getId(), user);
        return save();
    }

    @Override
    public User getUserById(UUID id) {
        return userMap.get(id);
    }


    @Override
    public List<MediaCollection> getPodcastsForUser(UUID userID) {
        User user = userMap.get(userID);
        return user.getUserPodcasts();
    }

    public User getUserByPodcastID(UUID podcastID){
        Podcast podcast = podcastDAO.getPodcastById(podcastID);
        return podcast.getCreatedBy();
    }
}
