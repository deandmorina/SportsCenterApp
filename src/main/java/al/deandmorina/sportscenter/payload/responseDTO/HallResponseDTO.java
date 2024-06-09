package al.deandmorina.sportscenter.payload.responseDTO;

import lombok.Data;

@Data
public class HallResponseDTO {
    private long id;
    private String name;
    private int normalSeats;
    private int VIPSeats;
    private int ultraSeats;
    private UserResponseDTO manager;
}
