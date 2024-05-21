package project.shimozukuri.banking.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.shimozukuri.banking.entities.bank.MoneyAccount;
import project.shimozukuri.banking.entities.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users", schema = "banking")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    private String username;

    @NotNull
    private String password;

    @Transient
    private String confirmPassword;

    @NotNull
    @ElementCollection
    private List<String> emails;

    @ElementCollection
    @Column(
            name = "phone_numbers",
            nullable = false
    )
    private List<String> phoneNumbers;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    private String name;

    private String surname;

    private String patronymic;

    @Column(name = "role")
    @ElementCollection
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @OneToOne
    @JoinTable(
            name = "users_accounts",
            schema = "banking",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private MoneyAccount moneyAccount;
}
