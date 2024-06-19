package al.deandmorina.sportscenter.payload.responseDTO;

import lombok.Data;

@Data
public class ReservationResponseDTO {
    private long id;
    private UserResponseDTO user;
    private EventResponseDTO event;
    private int normalSeats;
    private int VIPSeats;
    private int ultraSeats;
    private double normalSeatPrice;
    private double VIPSeatPrice;
    private double ultraSeatPrice;
    private double total;
}
