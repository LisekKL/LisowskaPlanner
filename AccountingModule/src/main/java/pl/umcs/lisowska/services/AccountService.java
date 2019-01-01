package pl.umcs.lisowska.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.lisowska.common.Account;
import pl.umcs.lisowska.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    private final AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account) {

        return accountRepository.save(account);
    }

    public List<Account> findAllAccounts() {
        Iterable<Account> accountsIterable = accountRepository.findAll();

        return (List<Account>) accountsIterable;
    }

    public List<Account> findAllAccountsForUser(Long userId) {
        Iterable<Account> accountsIterable = accountRepository.findByUserId(userId);

        return (List<Account>) accountsIterable;
    }



    public Account findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}

