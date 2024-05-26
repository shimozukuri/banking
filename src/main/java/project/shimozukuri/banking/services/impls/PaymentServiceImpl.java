package project.shimozukuri.banking.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.shimozukuri.banking.dtos.bank.PaymentDto;
import project.shimozukuri.banking.entities.bank.MoneyAccount;
import project.shimozukuri.banking.entities.bank.Payment;
import project.shimozukuri.banking.exceptions.ResourceNotFoundException;
import project.shimozukuri.banking.mappers.PaymentMapper;
import project.shimozukuri.banking.repositories.PaymentRepository;
import project.shimozukuri.banking.services.MoneyAccountService;
import project.shimozukuri.banking.services.PaymentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final MoneyAccountService moneyAccountService;
    private final UserServiceImpl userService;

    @Override
    @Transactional
    public List<Payment> getAllByUsername(String username) {
        Long moneyAccountId = userService.getUserByUsername(username).getMoneyAccount().getId();
        List<Payment> payments = paymentRepository.findAllByMoneyAccountId(moneyAccountId);

        if (payments.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found.");
        }

        return payments;
    }

    @Override
    @Transactional
    public Payment createPayment(PaymentDto paymentDto, String username) {
        MoneyAccount sender = userService.getUserByUsername(username).getMoneyAccount();
        MoneyAccount recipient = moneyAccountService.getById(paymentDto.getRecipientMoneyAccountId());

        if (paymentDto.getAmount() <= 0) {
            throw new IllegalStateException("Amount must be greater than 0.");
        }
        if (sender.equals(recipient)) {
            throw new IllegalStateException("You can't make a payment to yourself.");
        }

        if (sender.getBalance() - paymentDto.getAmount() >= 0) {
            sender.setBalance(sender.getBalance() - paymentDto.getAmount());
            sender.setMaxBalance(sender.getMaxBalance() - paymentDto.getAmount());
        } else {
            throw new IllegalStateException("Insufficient funds on balance.");
        }

        recipient.setBalance(recipient.getBalance() + paymentDto.getAmount());
        recipient.setMaxBalance(recipient.getMaxBalance() + paymentDto.getAmount());

        moneyAccountService.update(sender);
        moneyAccountService.update(recipient);

        Payment payment = paymentMapper.toEntity(paymentDto);
        payment.setSenderMoneyAccountId(sender.getId());
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

}
