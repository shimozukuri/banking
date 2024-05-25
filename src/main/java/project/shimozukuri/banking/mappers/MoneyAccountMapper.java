package project.shimozukuri.banking.mappers;

import org.mapstruct.Mapper;
import project.shimozukuri.banking.dtos.bank.MoneyAccountDto;
import project.shimozukuri.banking.entities.bank.MoneyAccount;

@Mapper(componentModel = "spring")
public interface MoneyAccountMapper extends Mappable<MoneyAccount, MoneyAccountDto> {
}
