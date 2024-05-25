package project.shimozukuri.banking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.shimozukuri.banking.entities.bank.MoneyAccount;

@Repository
public interface MoneyAccountRepository extends CrudRepository<MoneyAccount, Long> {
}
