package use_case.upload;
import java.util.Optional;
public interface UploadInputBoundary {
    // TODO: change return type to void after presenter is implemented
    public default Object execute(UploadInputData uploadInputData){
        return "place holder";
    }
}
