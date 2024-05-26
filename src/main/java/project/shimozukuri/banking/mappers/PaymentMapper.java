package project.shimozukuri.banking.mappers;

import org.mapstruct.Mapper;
import project.shimozukuri.banking.dtos.bank.PaymentDto;
import project.shimozukuri.banking.entities.bank.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends Mappable<Payment, PaymentDto> {
}
