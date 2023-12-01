package interface_adapter.display_podcast;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayPodcastViewModel extends ViewModel {

    private DisplayPodcastState displayPodcastState = new DisplayPodcastState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DisplayPodcastViewModel() {
        super("podcast");
    }

    public DisplayPodcastState getState(){
        return this.displayPodcastState;
    }

    public void setState(DisplayPodcastState state){
        this.displayPodcastState = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("podcast", null, this.displayPodcastState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
