package project.shimozukuri.banking.dtos.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDto {

    @Email(
            message = "Enter a valid email address."
    )
    private String email;
}
