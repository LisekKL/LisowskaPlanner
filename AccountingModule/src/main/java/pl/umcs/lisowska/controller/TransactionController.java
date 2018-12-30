package pl.umcs.lisowska.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.lisowska.model.Transaction;
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
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();

        log.info("Retrieve objects {}", transactions);

        return transactions;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(setTransactionDate(transaction));

        log.info("Add transaction {}", savedTransaction);

        return savedTransaction;
    }

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
        Transaction updatedTransaction = transactionService.updateTransaction(setTransactionDate(transaction));

        log.info("Updated Transaction {}", updatedTransaction);

        return updatedTransaction;
    }

    private Transaction setTransactionDate(Transaction transaction){
        transaction.setTransactionDate(new Date());
        return transaction;
    }
}
