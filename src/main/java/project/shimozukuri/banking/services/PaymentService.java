package project.shimozukuri.banking.services;

import project.shimozukuri.banking.dtos.bank.PaymentDto;
import project.shimozukuri.banking.entities.bank.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllByUsername(String username);

    Payment createPayment(PaymentDto paymentDto, String username);
}
