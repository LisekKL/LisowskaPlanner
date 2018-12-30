package pl.umcs.lisowska.repository;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.lisowska.common.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

