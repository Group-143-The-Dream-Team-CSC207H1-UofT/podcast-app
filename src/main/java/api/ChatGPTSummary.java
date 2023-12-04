package api;

import entities.TextChunk;
import entities.Transcript;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ChatGPTSummary implements SummaryAPIInterface {
    final private String OPENAI_API_KEY;

    public ChatGPTSummary(String apiKey) {
        OPENAI_API_KEY = apiKey;
    }

    /**
     * Uses the OpenAI ChatGPT API to generate a summary of a podcast based on its transcript
     * @param transcript the transcript of the podcast to generate a summary of
     * @return a String of the summary of the episode that was generated
     * @throws IOException
     */
    @Override
    public String generateSummary(Transcript transcript) throws IOException {
        // create the prompt using the podcast transcript
        StringBuilder rawText = new StringBuilder();
        for (TextChunk chunk: transcript.getTextChunks()) {
            rawText.append(chunk.getText());
        }
        String prompt = "Generate a short summary of the following podcast transcription: " + rawText;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        // This json is the body of the request
        String json = "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"system\",\"content\":\"You are generating summaries of podcast transcriptions.\"},{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";
        RequestBody requestBody = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            // If the request is successful, you can parse the response JSON
            String responseBody = response.body().string();
            JSONObject responseJSON = new JSONObject(responseBody);
            JSONArray choicesArray = responseJSON.getJSONArray("choices");
            JSONObject choice = choicesArray.getJSONObject(0);
            return choice.getJSONObject("message").getString("content");
        } else {
            System.err.println("Request failed with code: " + response.code());
            System.err.println(response.body().string());
            return null;
        }
    }
}
