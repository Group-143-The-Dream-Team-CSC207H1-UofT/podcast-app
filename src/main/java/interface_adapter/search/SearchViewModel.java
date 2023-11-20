package interface_adapter.search;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    private SearchState state = new SearchState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor
    public SearchViewModel() {
        super("search");
    }

    public SearchState getState() {
        return state;
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("search", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
