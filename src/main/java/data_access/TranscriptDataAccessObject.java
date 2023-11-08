package data_access;

import entities.Episode;
import entities.TextChunk;
import entities.Transcript;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TranscriptDataAccessObject implements TranscriptDataAccess {
    private final Map<UUID, Transcript> transcriptMap;

    public TranscriptDataAccessObject() {
        this.transcriptMap = new HashMap<>();
        loadTranscripts();
    }

    private void loadTranscripts() {
        File transcriptsCSV;
        try {
            transcriptsCSV = new File(this.getClass().getResource("/transcripts.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading transcripts.csv.");
            e.printStackTrace();
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(transcriptsCSV));
            reader.readLine();  // read the header row
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                UUID id = UUID.fromString(col[0]);
                String content = col[1];
                Transcript transcript = new Transcript(id, content, stringToChunks(content));
                transcriptMap.put(id, transcript);
            }
        } catch (IOException e) {
            System.out.println("Could not load transcripts from transcripts.csv.");
        }
    }

    private List<TextChunk> stringToChunks(String content) {
        ArrayList<TextChunk> textChunks = new ArrayList<>();
        String[] parts;
        String timestamp;
        String text;
        for (String chunkString : content.split("\n\n")) {
            parts = chunkString.split("\n");
            timestamp = parts[1];
            text = parts[2];
            textChunks.add(createTextChunk(timestamp, text));
        }
        return textChunks;
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
            return null;
        }
    }

    @Override
    public boolean saveTranscript(Transcript transcript) {
        transcriptMap.put(transcript.getId(), transcript);
        return save();
    }

    @Override
    public Transcript getTranscriptById(UUID id) {
        return transcriptMap.get(id);
    }

    private boolean save() {
        File transcriptsCSV;
        try {
            transcriptsCSV = new File(this.getClass().getResource("/transcripts.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error reading transcripts.csv.");
            e.printStackTrace();
            return false;
        }
        BufferedWriter writer;
        String transcriptString;
        try {
            writer = new BufferedWriter(new FileWriter(transcriptsCSV));
            writer.write("id,text\n");
            for (Transcript transcript : transcriptMap.values()) {
                transcriptString = String.format("%s,%s",
                        transcript.getId().toString(), transcript.getText());
                writer.write(transcriptString);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not save transcripts to transcripts.csv.");
            return false;
        }
    }
}
