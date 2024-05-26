package project.shimozukuri.banking.services;

import project.shimozukuri.banking.entities.bank.MoneyAccount;

public interface MoneyAccountService {

    MoneyAccount create(Double balance);

    MoneyAccount getById(Long id);

    MoneyAccount update(MoneyAccount moneyAccount);
}
