package interface_adapter.home;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {
    private HomeState homeState = new HomeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public HomeViewModel() {
        super("home");  // we might want to change this name at some point
    }

    public HomeState getState() {
        return homeState;
    }

    public void setState(HomeState homeState) {
        this.homeState = homeState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("allPodcasts",null,this.homeState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
