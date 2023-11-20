package entities;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;

    // the commented portions of this file are upgrades that can be implemented at a later time.

//    private final String password;
//    private final String email;
    private List<MediaCollection> userPodcasts;

    public User(UUID id, String username, List<MediaCollection> userPodcasts) {
        this.id = id;
        this.username = username;
//        this.password = password;
//        this.email = email;
        this.userPodcasts = userPodcasts;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public String getEmail() {
//        return email;
//    }

    public List<MediaCollection> getUserPodcasts() {
        return userPodcasts;
    }

}

