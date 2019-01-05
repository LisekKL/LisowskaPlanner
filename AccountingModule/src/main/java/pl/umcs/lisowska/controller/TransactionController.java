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
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private final TransactionService transactionService;
    @Autowired
    private final AccountService accountService;

    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }
    @Bean
    private RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();

        log.info("Retrieved transactions {}", transactions);

        return transactions;
    }

//    @PostMapping(consumes = APPLICATION_JSON_VALUE, path = "/{userId}/{accountId}")
//    public Transaction saveTransaction(@RequestBody Transaction transaction, @PathVariable Long senderId, @PathVariable Long recipientId) {
//        String url = discoveryClient.getNextServerFromEureka("accounts", false).getHomePageUrl();
//        ResponseEntity<Account> responseSender = restTemplate().getForEntity(url + "accounts/userId?" + senderId, Account.class);
//        Account accountSender = responseSender.getBody();
//
//        if(accountSender == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender accountSender with id " + senderId + " not found!"); }
//
//        transaction.setTransactionDate(new Date());
//        Transaction savedTransaction = transactionService.saveTransaction(transaction);
//
//        log.info("Added transaction {}", savedTransaction);
//
//        return savedTransaction;
//    }

    @GetMapping("/{id}")
    public Transaction findTransactionById(@PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);

        log.info("Delete transaction with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public Transaction updateTransaction(@RequestBody Transaction transaction) {


        transaction.setTransactionDate(new Date());
        Transaction updatedTransaction = transactionService.updateTransaction(transaction);

        log.info("Updated Transaction {}", updatedTransaction);

        return updatedTransaction;
    }

//    @PostMapping(produces = APPLICATION_JSON_VALUE)
//    public Transaction makeTransaction(@RequestParam("senderId") long senderId, @RequestParam("recipientId") long recipientId, @RequestParam("amount") double amount){
//        Account sender = accountService.findAccountById(senderId);
//        Account recipient = accountService.findAccountById(recipientId);
//
//        if(sender == null || recipient == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender or recipient not found!");
//        }
//        Transaction transaction = new Transaction(sender, recipient, amount);
//        transaction.setType(TransactionType.EXPENSE);
//        transaction.setStatus(TransactionStatus.ONGOING);
//        transaction.setTransactionDate(new Date());
//        transactionService.saveTransaction(transaction);
//
//        try {
//            sender.makeWithdrawal(amount);
//            recipient.makeDeposit(amount);
//        }catch(Exception ex){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during transaction: " + ex.getMessage());
//        }
//
//        return transactionService.saveTransaction(transaction);
//    }
}
