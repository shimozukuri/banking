package project.shimozukuri.banking.services.threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.shimozukuri.banking.entities.bank.MoneyAccount;
import project.shimozukuri.banking.services.MoneyAccountService;
import project.shimozukuri.banking.services.impls.UserServiceImpl;

import static java.util.concurrent.TimeUnit.MINUTES;
@Component
public class PercentThread {
    private final MoneyAccountService moneyAccountService;
    private final UserServiceImpl userService;

    @Autowired
    public PercentThread(MoneyAccountService moneyAccountService, @Lazy UserServiceImpl userService) {
        this.moneyAccountService = moneyAccountService;
        this.userService = userService;
    }

    public void startPercentThread(String username) {
        Thread percentThread = new Thread(() -> {
            try {
                boolean breakpoint = true;

                while (breakpoint) {
                    MINUTES.sleep(1);

                    synchronized (UserServiceImpl.class) {
                        synchronized (MoneyAccountService.class) {
                            MoneyAccount moneyAccount = userService.getUserByUsername(username).getMoneyAccount();
                            double balanceWithPercent = moneyAccount.getBalance() * 0.05 + moneyAccount.getBalance();

                            if (balanceWithPercent >= moneyAccount.getMaxBalance()) {
                                breakpoint = false;
                            }

                            moneyAccount.setBalance(balanceWithPercent);
                            moneyAccountService.update(moneyAccount);
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        percentThread.start();
    }
}
