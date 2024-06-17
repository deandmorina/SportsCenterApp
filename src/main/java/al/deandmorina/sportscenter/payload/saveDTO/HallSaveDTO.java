package al.deandmorina.sportscenter.payload.saveDTO;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class HallSaveDTO {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String hallType;
    @Min(0)
    private int normalSeats;
    @Min(0)
    private int VIPSeats;
    @Min(0)
    private int ultraSeats;
    private long managerId;
}
