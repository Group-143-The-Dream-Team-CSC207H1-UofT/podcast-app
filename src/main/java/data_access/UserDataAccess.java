package data_access;

import entities.MediaCollection;
import entities.User;
import java.util.List;
import java.util.UUID;

public interface UserDataAccess {

    public boolean saveUser(User user);

    public User getUserById(UUID id);

//    public User getUserByName(String username);

    public List<MediaCollection> getPodcastsForUser(UUID userID);

}
