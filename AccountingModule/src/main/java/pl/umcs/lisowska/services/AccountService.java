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
        Account savedAccount = accountRepository.save(account);

        return savedAccount;
    }

    public List<Account> findAllAccounts() {
        Iterable<Account> accountsIterable = accountRepository.findAll();
        List<Account> users = (List<Account>) accountsIterable;

        return users;
    }

    public Account findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        }
        return null;
    }

    public Account updateAccount(Account account) {
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}

