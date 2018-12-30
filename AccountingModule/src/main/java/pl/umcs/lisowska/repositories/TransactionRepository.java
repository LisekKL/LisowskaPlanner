package pl.umcs.lisowska.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.lisowska.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
