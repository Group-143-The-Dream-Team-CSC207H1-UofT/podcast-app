package interface_adapter.create_episode;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateEpisodeViewModel extends ViewModel {
    private CreateEpisodeState state = new CreateEpisodeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor
    public CreateEpisodeViewModel() {
        super("upload");
    }

    public CreateEpisodeState getState() {
        return state;
    }

    public void setState(CreateEpisodeState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("upload", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

