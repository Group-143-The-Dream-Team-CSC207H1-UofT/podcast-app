package api;

import okhttp3.*;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WhisperTranscription implements TranscriptionInterface {

    final private String OPENAI_API_KEY;
    final private int MAX_CHUNK_SIZE_BYTES = 20 * 1024 * 1024;

    public WhisperTranscription(String apiKey) {
        OPENAI_API_KEY = apiKey;
    }

    /**
     * Transcribes a given .wav file in SRT format.
     * @param file a .wav file that is to be transcribed
     * @return the transcription of file in SRT format
     * @throws IOException
     */
    @Override
    public String transcribeFile(File file) throws IOException {
        ArrayList<String> transcripts = new ArrayList<>();
        List<File> audioFileChunks = getAudioFileChunks(file);
        try {
            for (File chunk : audioFileChunks) {
                String s = transcribeChunk(chunk);
                transcripts.add(s);
            }
        } finally {
            for (File chunk : audioFileChunks) {
                if (!chunk.delete()) {
                    System.err.println(String.format("Failed to delete chunk file %s", chunk.getName()));
                }
            }
        }
        return joinTranscripts(transcripts);
    }

    private String transcribeChunk(File chunk) throws IOException {
        MediaType MEDIA_TYPE = MediaType.parse("audio/wav");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", chunk.getName(), RequestBody.create(chunk, MEDIA_TYPE))
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("response_format", "srt")
                .addFormDataPart("language", "en")
                .build();
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/audio/transcriptions")
                .addHeader("Authorization", String.format("Bearer %s", OPENAI_API_KEY))
                .addHeader("content-type", "multipart/form-data")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private List<File> getAudioFileChunks(File file) throws IOException {
        List<File> chunks = new ArrayList<>();
        int chunkNumber = 1;

        try (AudioInputStream inputStream = AudioSystem.getAudioInputStream(file)) {
            long totalFrames = inputStream.getFrameLength();
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            AudioFormat format = fileFormat.getFormat();

            int frameSize = format.getFrameSize(); // bytes per frame
            long framesPerChunk = MAX_CHUNK_SIZE_BYTES / frameSize;

            byte[] buffer = new byte[(int) (framesPerChunk * frameSize)];

            while (totalFrames > 0) {
                long chunkFrames = Math.min(totalFrames, framesPerChunk);
                int bytesRead = inputStream.read(buffer, 0, (int) (chunkFrames * frameSize));
                if (bytesRead > 0) {
                    File chunkFile = new File(file.getAbsolutePath().replace(
                            ".wav", String.format("-%d.wav", chunkNumber)));
                    try (AudioInputStream partStream = new AudioInputStream(
                            new ByteArrayInputStream(buffer, 0, bytesRead),
                            format,
                            chunkFrames)) {
                        AudioSystem.write(partStream, AudioFileFormat.Type.WAVE, chunkFile);
                    }
                    chunks.add(chunkFile);
                    chunkNumber++;
                }
                totalFrames -= chunkFrames;
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio format (only .wav files are supported).");
            e.printStackTrace();
        }

        return chunks;
    }

    private String joinTranscripts(List<String> transcripts) {
        StringBuilder joinedTranscript = new StringBuilder();
        Object[] data;
        int chunkOffset = 1;
        long timeOffset = 0;
        for (String transcript : transcripts) {
            data = shiftTranscript(transcript, chunkOffset, timeOffset);
            joinedTranscript.append((String) data[0]);
            chunkOffset = (int) data[1];
            timeOffset = (long) data[2];
        }
        return joinedTranscript.toString().stripTrailing();
    }

    private Object[] shiftTranscript(String transcript, int chunkOffset, long timeOffset) {
        StringBuilder shiftedTranscript = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        String[] timestamps;
        long start;
        long end = timeOffset;
        int currentChunk = chunkOffset;
        for (String chunk : transcript.strip().split("\n\n")) {
            // get the parts of the chunk
            String[] parts = chunk.split("\n");

            // add the new chunk number to the shifted transcript
            shiftedTranscript.append(currentChunk++).append("\n");

            // get the timestamps
            timestamps = parts[1].split(" --> ");
            // convert timestamps to milliseconds and increment
            try {
                start = dateFormat.parse(timestamps[0]).getTime() + timeOffset;
                end = dateFormat.parse(timestamps[1]).getTime() + timeOffset;
            } catch(ParseException e) {
                System.out.println("Error in SRT file format. Aborting.");
                return null;
            }
            // add new timestamps to shifted transcript
            shiftedTranscript.append(dateFormat.format(new Date(start))).append(" --> ")
                    .append(dateFormat.format(new Date(end))).append("\n");

            // add the text to shifted transcript
            shiftedTranscript.append(parts[2]).append("\n\n");
        }
        return new Object[]{shiftedTranscript.toString(), currentChunk, end - 18000000};
    }
}
