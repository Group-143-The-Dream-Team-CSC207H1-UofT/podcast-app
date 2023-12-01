package interface_adapter.podcast;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PodcastViewModel extends ViewModel {

    private PodcastState podcastState = new PodcastState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PodcastViewModel() {
        super("podcast");
    }

    public PodcastState getState(){
        return this.podcastState;
    }

    public void setState(PodcastState state){
        this.podcastState = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("podcast", null, this.podcastState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
