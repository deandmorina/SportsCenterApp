package al.deandmorina.sportscenter.payload.saveDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleSaveDTO {
    @NotEmpty(message = "Role name cannot be empty!")
    private String name;
}
