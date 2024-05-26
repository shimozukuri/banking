package project.shimozukuri.banking.entities.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "money_accounts", schema = "banking")
public class MoneyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @JsonIgnore
    @Column(name = "max_balance")
    private Double maxBalance;
}
