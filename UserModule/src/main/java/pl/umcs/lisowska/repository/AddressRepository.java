package pl.umcs.lisowska.repository;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.lisowska.common.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Iterable<Address> findByUserId(Long userId);
}

