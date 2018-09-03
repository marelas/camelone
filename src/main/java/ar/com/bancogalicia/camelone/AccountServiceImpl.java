package ar.com.bancogalicia.camelone;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component("accountService")
public class AccountServiceImpl implements  AccountService {

    private Map<Integer, Account> accounts = new HashMap<>();

    private final AtomicInteger autogeneratedID = new AtomicInteger();

    public AccountServiceImpl(){
        loadDummyAccounts();
    }

    @Override
    public String create(Account account) {
        int id =autogeneratedID.incrementAndGet();
        account.setId(id);
        accounts.put(id, account);
        return "" + id;
    }

    @Override
    public void update(Account account) {
        int id = account.getId();
        accounts.remove(id);
        accounts.put(id, account);

    }

    @Override
    public void close(int accountId) {
        accounts.remove(accountId);
    }

    @Override
    public Account findAccount(int accountId) {
        return  accounts.get(accountId);
    }


    private void loadDummyAccounts(){
        Account account1 = new Account();
        account1.setId(1);
        account1.setName("Luciano Pereyra");
        account1.setAmount(BigDecimal.valueOf(45.9));
        account1.setDateCreated(new Date());
        create(account1);

        Account account2 = new Account();
        account2.setId(autogeneratedID.incrementAndGet());
        account2.setName("Nito Artaza");
        account2.setAmount(BigDecimal.valueOf(45.9));
        account2.setDateCreated(new Date());
        create(account2);
    }

}