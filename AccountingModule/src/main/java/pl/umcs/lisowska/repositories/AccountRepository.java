package pl.umcs.lisowska.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.lisowska.common.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

//    @Query("SELECT acc FROM account acc WHERE acc.holder_id = :holder_id")
//    public List<Account> findAccountsForUser(@Param("holder_id") String holder_id);

    List<Account> findByUserId(Long userId);
}
