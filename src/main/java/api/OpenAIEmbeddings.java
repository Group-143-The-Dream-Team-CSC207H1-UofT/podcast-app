package api;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenAIEmbeddings implements EmbeddingsInterface {
    final private String OPENAI_API_KEY;

    public OpenAIEmbeddings(String apiKey) {
        OPENAI_API_KEY = apiKey;
    }
    @Override
    public float[] getEmbedding(String value) {
        List<String> values = new ArrayList<>();
        values.add(value);
        return getEmbeddings(values).get(0);
    }

    @Override
    public List<float[]> getEmbeddings(List<String> values) {
        OkHttpClient client = new OkHttpClient();

        StringBuilder content = new StringBuilder("{\"input\": [");
        for (String value : values) {
            content.append(String.format("\"%s\",", value));
        }
        content.deleteCharAt(content.length() - 1);  // remove the trailing comma
        content.append("],\"model\": \"text-embedding-ada-002\",\"encoding_format\": \"float\"}");

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(content.toString(), mediaType);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/embeddings")
                .post(body)
                .addHeader("Authorization", String.format("Bearer %s", OPENAI_API_KEY))
                .addHeader("content-type", "application/json")
                .build();

        String responseBody;
        try {
            responseBody = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            System.out.println("API call to embeddings failed.");
            e.printStackTrace();
            return null;
        }
        String regex = "\\\"embedding\\\": \\[\\n([e\\s0-9.,\\n-]*)\\]";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(responseBody);

        List<float[]> vectors = new ArrayList<>();
        while (matcher.find()) {
            String[] floats = matcher.group(1).split("\n");
            float[] vector = new float[1536];
            for (int i = 0; i < 1536; i++) {
                String strippedFloatString = floats[i].strip();
                vector[i] = Float.parseFloat(strippedFloatString.substring(0, strippedFloatString.length() - 1));
            }
            vectors.add(vector);
        }
        return vectors;
    }
}
