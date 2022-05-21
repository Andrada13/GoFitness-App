package backend.requests;
import javax.validation.constraints.*;
 
public class DeleteProgramRequest {
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @NotBlank
    private String time;
}
