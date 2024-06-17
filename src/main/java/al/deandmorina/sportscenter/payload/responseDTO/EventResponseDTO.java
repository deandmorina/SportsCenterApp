package al.deandmorina.sportscenter.payload.responseDTO;

import lombok.Data;

@Data
public class EventResponseDTO {
    private long id;
    private HallResponseDTO hall;
    private double normalSeatPrice;
    private double VIPSeatPrice;
    private double ultraSeatPrice;
    private Data startTime;
    private Data endTime;
    private String firstTeam;
    private String secondTeam;
}
