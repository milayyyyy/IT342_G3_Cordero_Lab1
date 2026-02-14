package coordero.it342.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 64)
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
