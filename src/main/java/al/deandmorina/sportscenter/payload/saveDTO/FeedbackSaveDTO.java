package al.deandmorina.sportscenter.payload.saveDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FeedbackSaveDTO {
    private long reservationId;
    @NotNull
    @NotEmpty
    private String message;
}
