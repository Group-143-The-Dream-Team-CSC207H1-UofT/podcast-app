package data_access;

import entities.TextChunk;
import entities.Transcript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class TranscriptDataAccessObject {
    private HashMap<String, Transcript> transcriptMap;
    private final String directory;

    public TranscriptDataAccessObject(String directory) {
        this.directory = directory;
        this.transcriptMap = new HashMap<>();
        loadTranscripts();
    }

    private void loadTranscripts() {
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                try {
                    transcriptMap.put(file.getName(), createTranscript(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Transcript createTranscript(File file) throws IOException {
        ArrayList<TextChunk> textChunks = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String timestamp;
        String text;
        while ((line = br.readLine()) != null) {
            if (line.equals("\n")) {
                continue;
            }
            timestamp = br.readLine();
            text = br.readLine();

            textChunks.add(createTextChunk(timestamp, text));
        }
        return new Transcript("", textChunks);
    }

    private TextChunk createTextChunk(String timestamp, String text) {
        String[] timestamps = timestamp.split(" --> ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        try {
            long start = dateFormat.parse(timestamps[0]).getTime();
            long end = dateFormat.parse(timestamps[0]).getTime();
            return new TextChunk(start, end, text);
        } catch (ParseException e) {
            System.out.println("Error in timestamp format in transcript file.");
            e.printStackTrace();

            // TODO: do something if there is a formatting error. Shouldn't happen.
            return null;
        }
    }
}
