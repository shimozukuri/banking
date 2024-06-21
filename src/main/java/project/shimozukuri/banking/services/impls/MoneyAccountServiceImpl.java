package project.shimozukuri.banking.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.shimozukuri.banking.entities.bank.MoneyAccount;
import project.shimozukuri.banking.exceptions.ResourceNotFoundException;
import project.shimozukuri.banking.repositories.MoneyAccountRepository;
import project.shimozukuri.banking.services.MoneyAccountService;

@Service
@RequiredArgsConstructor
public class MoneyAccountServiceImpl implements MoneyAccountService {
    private final MoneyAccountRepository moneyAccountRepository;

    @Override
    public MoneyAccount create(Double balance) {
        if (balance <= 0) {
            throw new IllegalStateException("Balance must be greater than 0.");
        }

        MoneyAccount moneyAccount = new MoneyAccount();
        moneyAccount.setBalance(balance);
        moneyAccount.setMaxBalance(balance * 2.07);

        return moneyAccountRepository.save(moneyAccount);
    }

    public MoneyAccount getById(Long id) {
        return moneyAccountRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Money account not found.")
        );
    }

    public synchronized MoneyAccount update(MoneyAccount moneyAccount) {
        return moneyAccountRepository.save(moneyAccount);
    }
}
