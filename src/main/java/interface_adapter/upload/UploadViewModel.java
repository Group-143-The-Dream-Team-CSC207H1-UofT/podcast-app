package interface_adapter.upload;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UploadViewModel {
    private String filePath;
    private PropertyChangeSupport propertyChangeSupport;

    // Constructor
    public UploadViewModel() {
        this.filePath = null; // No file selected initially
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String newFilePath) {
        String oldFilePath = this.filePath;
        this.filePath = newFilePath;
        propertyChangeSupport.firePropertyChange("filePath", oldFilePath, newFilePath);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void fileSelected(String selectedFilePath) {
        setFilePath(selectedFilePath);
    }

}

