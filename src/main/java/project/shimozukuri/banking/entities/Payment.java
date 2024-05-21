package project.shimozukuri.banking.entities;

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

    private Double sum;

    @Column(name = "sender_id")
    private Long senderMoneyAccount;

    @Column(name = "recipient_id")
    private Long recipientMoneyAccount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}
