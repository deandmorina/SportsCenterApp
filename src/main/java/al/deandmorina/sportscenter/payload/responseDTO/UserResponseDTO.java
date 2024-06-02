package al.deandmorina.sportscenter.payload.responseDTO;

import al.deandmorina.sportscenter.entity.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

}
