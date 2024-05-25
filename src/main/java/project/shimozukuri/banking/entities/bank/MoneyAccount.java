package project.shimozukuri.banking.entities.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.shimozukuri.banking.entities.user.User;

@Entity
@Data
@Table(name = "money_accounts", schema = "banking")
public class MoneyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double balance;

    @JsonIgnore
    @NotNull
    @Column(name = "max_balance")
    private Double maxBalance;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            unique = true
    )
    private User user;
}
