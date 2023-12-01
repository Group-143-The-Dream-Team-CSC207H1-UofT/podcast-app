package interface_adapter.episode;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EpisodeViewModel extends ViewModel {
    private EpisodeState state = new EpisodeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public EpisodeViewModel() {
        super("episode");
    }

    public EpisodeState getState() {
        return state;
    }
    public void setState(EpisodeState state) {
        this.state = state;
    }

}
