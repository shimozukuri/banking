package project.shimozukuri.banking.dtos.bank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {

    @NotNull(
            message = "Payment amount must be greater than null."
    )
    private Double amount;

    @NotNull(
            message = "Specify recipient."
    )
    private Long recipientMoneyAccountId;
}
