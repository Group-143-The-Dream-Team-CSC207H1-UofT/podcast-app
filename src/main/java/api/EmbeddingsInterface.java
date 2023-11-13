package api;

import java.util.List;

public interface EmbeddingsInterface {
    float[] getEmbedding(String value);

    List<float[]> getEmbeddings(List<String> values);
}
