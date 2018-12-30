package pl.umcs.lisowska.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.lisowska.model.Transaction;
import pl.umcs.lisowska.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);

        return savedTransaction;
    }

    public List<Transaction> findAllTransactions() {
        Iterable<Transaction> transactionsIterable = transactionRepository.findAll();
        List<Transaction> users = (List<Transaction>) transactionsIterable;

        return users;
    }

    public Transaction findTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        }
        return null;
    }

    public Transaction updateTransaction(Transaction transaction) {
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return updatedTransaction;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
