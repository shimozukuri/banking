package project.shimozukuri.banking.dtos.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserDto {

    @Length(
            min = 2,
            max = 50,
            message = "Username length must be at least 2 and smaller than 50 symbols."
    )
    private String username;

    @Length(
            min = 8,
            max = 255,
            message = "Password must be at least 8 characters long."
    )
    private String password;

    @NotNull(
            message = "Password confirmation must be not null."
    )
    private String confirmPassword;

    @Pattern(
            regexp = "^(8)\\d{10}$",
            message = "Phone number does not match the format: 8XXXXXXXXXX."
    )
    private String phoneNumber;

    @Pattern(
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "Enter a valid email address."
    )
    private String email;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE
    )
    @JsonFormat(
            pattern = "dd.MM.yyyy"
    )
    private LocalDate birthDay;

    @Length(
            max = 30,
            message = "Name length must be smaller than 30 symbols."
    )
    private String name;

    @NotNull(
            message = "Balance must be greater than null."
    )
    private Double balance;
}
