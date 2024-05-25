package project.shimozukuri.banking.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.shimozukuri.banking.entities.bank.MoneyAccount;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.exceptions.ResourceNotFoundException;
import project.shimozukuri.banking.repositories.MoneyAccountRepository;
import project.shimozukuri.banking.services.MoneyAccountService;

@Service
@RequiredArgsConstructor
public class MoneyAccountServiceImpl implements MoneyAccountService {
    private final MoneyAccountRepository moneyAccountRepository;

    @Override
    public MoneyAccount create(Double balance, User user) {
        if (balance <= 0) {
            throw new IllegalStateException("The balance cannot be negative.");
        }

        MoneyAccount moneyAccount = new MoneyAccount();
        moneyAccount.setBalance(balance);
        moneyAccount.setMaxBalance(balance * 2.07);
        moneyAccount.setUser(user);

        moneyAccountRepository.save(moneyAccount);

        return moneyAccount;
    }

    public MoneyAccount getById(Long id) {
        return moneyAccountRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Money account not found.")
        );
    }

    public MoneyAccount update(MoneyAccount moneyAccount) {
        return moneyAccount;
    }
}
