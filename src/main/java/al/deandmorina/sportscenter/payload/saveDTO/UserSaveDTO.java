package al.deandmorina.sportscenter.payload.saveDTO;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserSaveDTO {
    @NotEmpty
    @NotNull
    @Max(30)
    private String firstName;

    @NotEmpty
    @NotNull
    @Max(30)
    private String lastName;

    @Email(message = "Incorrect email format!")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String password;
}
