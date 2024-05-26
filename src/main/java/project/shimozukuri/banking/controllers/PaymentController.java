package project.shimozukuri.banking.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.shimozukuri.banking.dtos.bank.PaymentDto;
import project.shimozukuri.banking.entities.bank.Payment;
import project.shimozukuri.banking.services.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{username}")
    public List<Payment> getAllByUsername(
            @PathVariable(value = "username") String username
    ) {
        return paymentService.getAllByUsername(username);
    }

    @PostMapping("/{username}")
    public Payment createPayment(
            @PathVariable(value = "username") String username,
            @RequestBody @Valid PaymentDto paymentDto
    ) {
        return paymentService.createPayment(paymentDto, username);
    }
}
