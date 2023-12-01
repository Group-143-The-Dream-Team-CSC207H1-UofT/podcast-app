package interface_adapter.display_podcast;

import entities.Podcast;

public class DisplayPodcastState {

    private Podcast podcast;
    private String errorMessage;
    public DisplayPodcastState() {}

    public DisplayPodcastState(Podcast podcast, String errorMessage){
        this.podcast = podcast;
        this.errorMessage = errorMessage;
    }
    public Podcast getPodcast(){
        return this.podcast;
    }
    public void setPodcast(Podcast podcast){
        this.podcast = podcast;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }


}
