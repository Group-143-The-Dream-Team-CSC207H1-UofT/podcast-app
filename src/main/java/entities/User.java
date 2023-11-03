package entities;
import java.util.*;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Podcast> podcastsCreated;

    public User(int id, String username, String password, String email, List<Podcast> podcastsCreated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.podcastsCreated = podcastsCreated;
    }

}

