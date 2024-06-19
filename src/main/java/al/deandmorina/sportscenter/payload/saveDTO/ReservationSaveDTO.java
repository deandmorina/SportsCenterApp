package al.deandmorina.sportscenter.payload.saveDTO;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ReservationSaveDTO {
    private long eventId;
    @Min(0)
    private int normalSeats;
    @Min(0)
    private int VIPSeats;
    @Min(0)
    private int ultraSeats;
}
