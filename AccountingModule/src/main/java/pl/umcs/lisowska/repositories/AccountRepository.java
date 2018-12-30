package pl.umcs.lisowska.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.lisowska.common.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
