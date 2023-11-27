package interface_adapter.display_podcasts;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayPodcastsViewModel extends ViewModel {
    private DisplayPodcastsState displayPodcastsState = new DisplayPodcastsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public DisplayPodcastsViewModel() {
        super("Home");  // we might want to change this name at some point
    }

    public DisplayPodcastsState getState() {
        return displayPodcastsState;
    }

    public void setState(DisplayPodcastsState displayPodcastsState) {
        this.displayPodcastsState = displayPodcastsState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("allPodcasts",null,this.displayPodcastsState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
