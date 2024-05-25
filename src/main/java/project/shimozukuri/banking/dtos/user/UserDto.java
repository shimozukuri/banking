package project.shimozukuri.banking.dtos.user;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private List<String> phoneNumbers;
    private List<String> emails;
    private LocalDate birthDay;
    private String name;
    private String surname;
    private String patronymic;
    private Double balance;

    public UserDto(
            String username,
            String password,
            List<String> phoneNumbers,
            List<String> emails,
            Double balance
    ) {
        this.username = username;
        this.password = password;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
        this.balance = balance;
    }
}
