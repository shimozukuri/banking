package project.shimozukuri.banking.dtos.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PhoneNumberDto {

    @Pattern(
            regexp = "^(8)\\d{10}$",
            message = "Phone number must be in the format: 8XXXXXXXXXX."
    )
    private String phoneNumber;
}
