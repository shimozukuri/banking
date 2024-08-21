package project.shimozukuri.banking.entities.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @JsonIgnore
    @Transient
    private String confirmPassword;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "birth_day")
    private LocalDate birthDay;

    private String name;

    @Column(name = "email")
    @ElementCollection
    @CollectionTable(name = "users_emails")
    private List<String> emails;

    @Column(name = "phone_number")
    @ElementCollection
    @CollectionTable(name = "users_phone_numbers")
    private List<String> phoneNumbers;

    @Column(name = "role")
    @ElementCollection
    @CollectionTable(name = "users_roles")
    @JsonIgnore
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

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }
}
