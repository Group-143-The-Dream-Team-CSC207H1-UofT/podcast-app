package data_access;

import java.util.List;

public interface VectorDatabase {
    boolean insertVector(float[] vector, String id);

    boolean insertVectors(List<float[]> vectors, List<String> ids);

    List<String> query(float[] vector);
}
