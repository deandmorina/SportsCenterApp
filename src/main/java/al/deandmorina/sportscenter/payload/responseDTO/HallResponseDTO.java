package al.deandmorina.sportscenter.payload.responseDTO;

import al.deandmorina.sportscenter.entity.HallType;
import lombok.Data;

@Data
public class HallResponseDTO {
    private long id;
    private String name;
    private HallType hallType;
    private int normalSeats;
    private int VIPSeats;
    private int ultraSeats;
    private UserResponseDTO manager;
}
