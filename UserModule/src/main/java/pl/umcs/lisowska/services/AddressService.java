package pl.umcs.lisowska.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.umcs.lisowska.common.Address;
import pl.umcs.lisowska.repository.AddressRepository;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {
    private final AddressRepository addressRepository;


    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    public List<Address> findAllForUser(@PathVariable (value="userId") Long userId){
        return (List<Address>) addressRepository.findByUserId(userId);
    }

    public Address find(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElse(null);
    }

    public Address update(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

}

