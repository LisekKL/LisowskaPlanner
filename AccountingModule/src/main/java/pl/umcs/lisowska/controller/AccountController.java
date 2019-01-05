package pl.umcs.lisowska.controller;

import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.lisowska.common.Account;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.model.Transaction;
import pl.umcs.lisowska.model.enums.TransactionStatus;
import pl.umcs.lisowska.model.enums.TransactionType;
import pl.umcs.lisowska.services.AccountService;
import pl.umcs.lisowska.services.TransactionService;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    @Autowired
    private EurekaClient discoveryClient;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    public AccountController(AccountService accountService, EurekaClient discoveryClient, TransactionService transactionService) {
        this.accountService = accountService;
        this.discoveryClient = discoveryClient;
        this.transactionService = transactionService;
    }

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Account> getAccountsForUser(@RequestParam("userId") long userId) {
        if (userId < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID has to be a valid number >= 1!");
        }

        String url = discoveryClient.getNextServerFromEureka("users", false).getHomePageUrl();
        ResponseEntity<User> response = restTemplate().getForEntity(url + "users/" + userId, User.class);
        User user = response.getBody();

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        List<Account> accounts = accountService.findAllAccountsForUser(user.getId());
        log.info("User id: " + userId + " | Accounts: {}", accounts);

        return accounts;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/{accountId}")
    public Account getAccountById(@PathVariable long accountId) {
        return accountService.findAccountById(accountId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, path = "/{userId}")
    public Account saveAccount(@PathVariable long userId, @RequestBody Account accountRequest) {
        //long userId = accountRequest.getUser().getId();
        String url = discoveryClient.getNextServerFromEureka("users", false).getHomePageUrl();
        ResponseEntity<User> response = restTemplate().getForEntity(url + "users/" + userId, User.class);
        User user = response.getBody();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        accountRequest.setUser(user);
        Account savedAccount = accountService.saveAccount(accountRequest);

        log.info("Added account {}", savedAccount);

        return savedAccount;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);

        log.info("Deleted account with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, path = "/{userId}")
    public Account updateAccount(@RequestBody Account account, @PathVariable long userId) {
        //long userId = account.getUser().getId();
        String url = discoveryClient.getNextServerFromEureka("users", false).getHomePageUrl();
        ResponseEntity<User> response = restTemplate().getForEntity(url + "users/" + userId, User.class);
        User user = response.getBody();
        if (user == null) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"); }
        account.setUser(user);
        Account updatedAccount = accountService.updateAccount(account);

        log.info("Updated Account {}", updatedAccount);

        return updatedAccount;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/{senderId}/transaction")
    public Transaction makeTransaction(@PathVariable long senderId, @RequestParam("recipientId") long recipientId, @RequestParam("amount") double amount){
        Account sender = accountService.findAccountById(senderId);
        Account recipient = accountService.findAccountById(recipientId);

        if(sender == null || recipient == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender or recipient not found!");
        }
        Transaction transaction = new Transaction(sender, recipient, amount);
        transaction.setType(TransactionType.EXPENSE);
        transaction.setStatus(TransactionStatus.ONGOING);
        transaction.setTransactionDate(new Date());
        transaction = transactionService.saveTransaction(transaction);

        try {
            sender.makeWithdrawal(amount);
            recipient.makeDeposit(amount);
        }catch(Exception ex){
            transaction.setStatus(TransactionStatus.ERROR);
            transaction.setTransactionDate(new Date());
            transactionService.updateTransaction(transaction);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during transaction: " + ex.getMessage());
        }

        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setTransactionDate(new Date());
        transactionService.updateTransaction(transaction);

        return transactionService.saveTransaction(transaction);
    }
}
