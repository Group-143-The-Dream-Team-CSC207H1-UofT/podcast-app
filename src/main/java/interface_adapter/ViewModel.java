package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return this.viewName;
    }

    /**
     * Notifies all registered listeners about a change in the state.
     * This method triggers a property change event, signaling to all registered
     * listeners that the state has changed. It uses 'state' as the property name.
     */
    public abstract void firePropertyChanged();

    /**
     * Registers a PropertyChangeListener with this object.
     * Listeners registered via this method will be notified when the object's state changes.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
