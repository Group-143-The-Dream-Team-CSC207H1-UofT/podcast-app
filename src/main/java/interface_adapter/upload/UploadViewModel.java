package interface_adapter.upload;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;

public class UploadViewModel extends ViewModel {
    private UploadState uploadState = new UploadState();;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);;

    // Constructor
    public UploadViewModel() {
        super("upload");
    }

    public UploadState getState() {
        return uploadState;
    }

    public void setState(UploadState state) {
        this.uploadState = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("upload", null, this.uploadState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

