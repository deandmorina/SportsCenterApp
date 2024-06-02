package al.deandmorina.sportscenter.payload.updateDTO;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class PasswordUpdateDTO {
    private String oldPassword;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String newPassword;
    private String confirmPassword;
}
