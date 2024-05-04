package al.deandmorina.sportscenter.payload.saveDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RoleSaveDTO {
    @NotEmpty(message = "Role name cannot be empty!")
    private String name;
}
