package al.deandmorina.sportscenter.payload.responseDTO;

import lombok.Data;

import java.util.Date;

@Data
public class FeedbackResponseDTO {
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private ReservationResponseDTO reservation;
    private String message;
}
