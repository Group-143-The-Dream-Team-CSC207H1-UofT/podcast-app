package interface_adapter.create_podcast;

import interface_adapter.ViewModel;
import interface_adapter.create_episode.CreateEpisodeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreatePodcastViewModel extends ViewModel {

    private CreatePodcastState state = new CreatePodcastState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor
    public CreatePodcastViewModel() {
        super("create podcast");
    }

    public CreatePodcastState getState() {
        return state;
    }

    public void setState(CreatePodcastState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("create podcast", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
