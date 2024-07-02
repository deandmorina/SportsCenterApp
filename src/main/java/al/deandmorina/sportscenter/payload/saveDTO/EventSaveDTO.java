package al.deandmorina.sportscenter.payload.saveDTO;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EventSaveDTO {
    private long hallId;
    @Min(0)
    private double normalSeatPrice;
    @Min(0)
    private double VIPSeatPrice;
    @Min(0)
    private double ultraSeatPrice;
    @NotNull
    private Data startTime;
    @NotNull
    private Data endTime;
    @NotNull
    @NotEmpty
    private String firstTeam;
    @NotNull
    @NotEmpty
    private String secondTeam;
}
