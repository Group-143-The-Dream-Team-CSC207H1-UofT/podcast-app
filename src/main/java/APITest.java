import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class APITest {

    public static void main(String[] args) throws IOException {
        System.out.println(transcribeFile(new File("src/main/resources/test_audio.m4a")));
    }

    static String transcribeFile(File file) throws IOException {
        String OPENAI_API_KEY = "API KEY GOES HERE";
        MediaType MEDIA_TYPE = MediaType.parse("audio/m4a");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE, file))
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("response_format", "text")
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
}