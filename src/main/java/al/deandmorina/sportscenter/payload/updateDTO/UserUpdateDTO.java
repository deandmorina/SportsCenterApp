package al.deandmorina.sportscenter.payload.updateDTO;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateDTO {

    @NotEmpty
    @NotNull
    @Max(30)
    private String firstName;

    @NotEmpty
    @NotNull
    @Max(30)
    private String lastName;
}
