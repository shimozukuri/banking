package project.shimozukuri.banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
    @JoinColumn(name = "user_id")
    private User user;
}
