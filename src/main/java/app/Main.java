package app;

import javax.swing.JFrame;

import api.EmbeddingsInterface;
import api.OpenAIEmbeddings;
import api.WhisperTranscription;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.transcribe.TranscribeViewModel;
import interface_adapter.upload.*;
import view.*;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
        String PINECONE_API_KEY = System.getenv("PINECONE_API_KEY");
        String PINECONE_BASE_URL = System.getenv("PINECONE_BASE_URL");
        WhisperTranscription transcriptionObject = new WhisperTranscription(OPENAI_API_KEY);
        EmbeddingsInterface embeddings = new OpenAIEmbeddings(OPENAI_API_KEY);
        VectorDatabase vectorDatabase = new PineconeVectorDatabase(PINECONE_API_KEY, PINECONE_BASE_URL);

        TranscriptDataAccess transcriptDataAccessObject = new TranscriptDataAccessObject();
        EpisodeDataAccess episodeDataAccessObject = new EpisodeDataAccessObject(transcriptDataAccessObject);
        UploadViewModel uploadViewModel = new UploadViewModel();
        TranscribeViewModel transcribeViewModel = new TranscribeViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        UploadView uploadView = UploadUseCaseFactory.create(viewManagerModel, uploadViewModel, transcribeViewModel, episodeDataAccessObject, transcriptDataAccessObject, transcriptionObject, vectorDatabase, embeddings);

        // Set up the main window (a JFrame)
        JFrame frame = new JFrame("Upload Podcast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(uploadView);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
