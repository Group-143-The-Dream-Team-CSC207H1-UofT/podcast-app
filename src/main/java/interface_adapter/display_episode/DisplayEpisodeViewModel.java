package interface_adapter.display_episode;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayEpisodeViewModel extends ViewModel {
    private DisplayEpisodeState state = new DisplayEpisodeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public DisplayEpisodeViewModel() {
        super("episode");
    }

    public DisplayEpisodeState getState() {
        return state;
    }
    public void setState(DisplayEpisodeState state) {
        this.state = state;
    }

}
