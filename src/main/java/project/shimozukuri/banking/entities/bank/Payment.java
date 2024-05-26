package project.shimozukuri.banking.entities.bank;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments", schema = "banking")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Column(name = "sender_id")
    private Long senderMoneyAccountId;

    @Column(name = "recipient_id")
    private Long recipientMoneyAccountId;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}
