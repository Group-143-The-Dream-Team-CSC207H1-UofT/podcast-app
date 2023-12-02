package interface_adapter.transcribe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TranscribeViewModel extends ViewModel {
    private TranscribeState transcribeState = new TranscribeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor
    public TranscribeViewModel() {
        super("transcribe");
    }

    public TranscribeState getState() {
        return transcribeState;
    }

    public void setState(TranscribeState state) {
        this.transcribeState = state;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("transcribe", null, this.transcribeState);
    }
    /**
     * {@inheritDoc}
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
