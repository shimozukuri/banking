package project.shimozukuri.banking.services;

import project.shimozukuri.banking.entities.bank.MoneyAccount;
import project.shimozukuri.banking.entities.user.User;

public interface MoneyAccountService {

    MoneyAccount create(Double balance, User user);

    MoneyAccount getById(Long id);

    MoneyAccount update(MoneyAccount moneyAccount);
}
