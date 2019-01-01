package pl.umcs.lisowska.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.lisowska.common.Account;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.model.Transaction;
import pl.umcs.lisowska.model.enums.TransactionStatus;
import pl.umcs.lisowska.services.AccountService;
import pl.umcs.lisowska.services.TransactionService;
import pl.umcs.lisowska.services.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, TransactionService transactionService, UserService userService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public String invalidPath() {
        return "Please supply a user ID! A valid path should contain: /accounts/{userId}/";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/{userId}")
    public List<Account> getAccounts(@PathVariable String userId) {
        //List<Account> accounts = accountService.findAllAccountsForUser(userId);
        User user = userService.find(Long.parseLong(userId));

        List<Account> accounts = accountService.findAllAccountsForUser(Long.parseLong(userId));
        log.info("User id: " + userId + " | Accounts: {}", accounts);

        return accounts;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, path = "/{userId}")
    public Account saveAccount(@PathVariable String userId, @RequestBody Account accountRequest) {
        User user = userService.find(Long.parseLong(userId));
        accountRequest.setUser(user);
        Account savedAccount = accountService.saveAccount(accountRequest);

        log.info("Added account {}", savedAccount);

        return savedAccount;
    }

//    @GetMapping("/{id}")
//    public Account findAccountById(@PathVariable Long id) {
//        return accountService.findAccountById(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);

        log.info("Deleted account with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, path = "/{userId}")
    public Account updateAccount(@PathVariable String userId, @RequestBody Account account) {
        User user = userService.find(Long.parseLong(userId));
        account.setUser(user);
        Account updatedAccount = accountService.updateAccount(account);

        log.info("Updated Account {}", updatedAccount);

        return updatedAccount;
    }

    private void makeSubtraction(Account sender, Account recipient, double amount) throws Exception {
        if(amount > sender.getBalance()){
            throw new Exception("Balance from sender is too low! Please choose a different amount or add money to account " + sender.getAccountNumber());
        }
        Transaction transaction = new Transaction(sender, recipient, amount);
        transaction.setStatus(TransactionStatus.STARTED);

        transaction = transactionService.saveTransaction(transaction);

        subtractMoney(sender, amount);
        addMoney(recipient, amount);

        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionService.updateTransaction(transaction);

        log.info("[TRANSACTION] Transferred [] from acc. nr. [] to ", amount, sender.getAccountNumber(), recipient.getAccountNumber());
    }

    private void subtractMoney(Account account, double amount){
        account.setBalance(account.getBalance() - amount);
    }
    private void addMoney(Account account, double amount){
        account.setBalance(account.getBalance() + amount);

        log.info("Added [] to account number []. Remaining balance: ", amount, account.getAccountNumber(), account.getBalance());
    }

/*    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public boolean executeTransaction(Account account, Transaction transaction){
        if(transaction.getType() == TransactionType.EXPENSE) {
            try {
                makeSubtraction(account, transaction.getAmount());
            } catch (Exception e) {
                log.info("Error executing transaction: ", e.getMessage());
                return false;
            }
        }
        return true;
    }*/
}
