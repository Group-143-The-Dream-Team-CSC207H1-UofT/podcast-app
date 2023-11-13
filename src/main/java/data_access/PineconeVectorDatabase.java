package data_access;

import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PineconeVectorDatabase implements VectorDatabase {
    private final String PINECONE_API_KEY;
    private final String baseURL;

    public PineconeVectorDatabase(String apiKey, String baseURL) {
        PINECONE_API_KEY = apiKey;
        this.baseURL = baseURL;
    }

    @Override
    public boolean insertVector(float[] vector, String id) {
        List<float[]> vectors = new ArrayList<>();
        vectors.add(vector);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        return insertVectors(vectors, ids);
    }

    @Override
    public boolean insertVectors(List<float[]> vectors, List<String> ids) {
        OkHttpClient client = new OkHttpClient();
        String content = toJSON(vectors, ids);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(content, mediaType);
        Request request = new Request.Builder()
                .url(String.format("%s/vectors/upsert", baseURL))
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("Api-Key", PINECONE_API_KEY)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("There was an error in the API call.");
            return false;
        }
        return response.isSuccessful();
    }

    private String toJSON(List<float[]> vectors, List<String> ids) {
        StringBuilder content = new StringBuilder("{\"vectors\":[");
        for (int i = 0; i < vectors.size(); i++) {
            content.append(String.format("{\"id\":\"%s\",", ids.get(i)));
            content.append("\"values\":[");
            for (float value : vectors.get(i)) {
                content.append(String.format("%f,", value));
            }
            content.deleteCharAt(content.length() - 1);  // remove the trailing comma
            content.append("]},");
        }
        content.append("]}");

        return content.toString();
    }

    @Override
    public List<String> query(float[] vector) {
        OkHttpClient client = new OkHttpClient();

        StringBuilder content = new StringBuilder("{\"includeValues\":\"false\",\"includeMetadata\":\"false\",\"topK\":10,\"vector\":[");
        for (float value : vector) {
            content.append(String.format("%f,", value));
        }
        content.deleteCharAt(content.length() - 1);
        content.append("]}");

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(content.toString(), mediaType);
        Request request = new Request.Builder()
                .url(String.format("%s/query", baseURL))
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("Api-Key", PINECONE_API_KEY)
                .build();

        String responseBody;
        try {
            responseBody = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            System.out.println("There was an error in the API call.");
            return null;
        }

        String regex = "\\\"id\\\":\\\"([a-zA-Z0-9-]*)\\\"";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(responseBody);

        List<String> ids = new ArrayList<>();
        while (matcher.find()) {
            ids.add(matcher.group(1));
        }
        return ids;
    }
}
