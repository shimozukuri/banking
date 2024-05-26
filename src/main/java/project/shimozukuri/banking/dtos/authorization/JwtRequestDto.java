package project.shimozukuri.banking.dtos.authorization;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequestDto {

    @NotNull(
            message = "Username must be not null."
    )
    private String username;

    @NotNull(
            message = "Password must be not null."
    )
    private String password;
}
