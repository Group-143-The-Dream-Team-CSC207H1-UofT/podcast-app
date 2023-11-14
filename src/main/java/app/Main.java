package app;

import javax.swing.*;

import api.EmbeddingsInterface;
import api.OpenAIEmbeddings;
import api.WhisperTranscription;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_index.SearchIndexViewModel;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.upload.*;
import view.*;

import java.awt.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
        String PINECONE_API_KEY = System.getenv("PINECONE_API_KEY");
        String PINECONE_BASE_URL = System.getenv("PINECONE_BASE_URL");

        JFrame application = new JFrame("Show Notes");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        WhisperTranscription transcriptionObject = new WhisperTranscription(OPENAI_API_KEY);
        EmbeddingsInterface embeddings = new OpenAIEmbeddings(OPENAI_API_KEY);
        VectorDatabase vectorDatabase = new PineconeVectorDatabase(PINECONE_API_KEY, PINECONE_BASE_URL);

        TranscriptDataAccess transcriptDataAccessObject = new TranscriptDataAccessObject();
        EpisodeDataAccess episodeDataAccessObject = new EpisodeDataAccessObject(transcriptDataAccessObject);
        UploadViewModel uploadViewModel = new UploadViewModel();
        TranscribeViewModel transcribeViewModel = new TranscribeViewModel();
        SearchIndexViewModel searchIndexViewModel = new SearchIndexViewModel();
        UploadView uploadView = UploadViewFactory.create(viewManagerModel, uploadViewModel, transcribeViewModel, searchIndexViewModel, episodeDataAccessObject, transcriptDataAccessObject, transcriptionObject, vectorDatabase, embeddings);

        views.add(uploadView, uploadView.viewName);

        viewManagerModel.setActiveView(uploadView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
