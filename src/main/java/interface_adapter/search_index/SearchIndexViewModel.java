package interface_adapter.search_index;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchIndexViewModel extends ViewModel {
    private SearchIndexState state = new SearchIndexState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor
    public SearchIndexViewModel() {
        super("search index");
    }

    public SearchIndexState getState() {
        return state;
    }

    public void setState(SearchIndexState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("search index", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
