package entities;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String password;
    private final String email;
    private List<Podcast> userPodcasts;

    public User(UUID id, String username, String password, String email, List<Podcast> userPodcasts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userPodcasts = userPodcasts;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Podcast> getUserPodcasts() {
        return userPodcasts;
    }

}

