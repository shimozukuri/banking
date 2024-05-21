package project.shimozukuri.banking.dtos.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequestDto {
    private String username;
    private String password;
}
