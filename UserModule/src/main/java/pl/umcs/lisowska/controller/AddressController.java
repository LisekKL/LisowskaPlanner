package pl.umcs.lisowska.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.lisowska.common.Address;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.services.AddressService;
import pl.umcs.lisowska.services.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/users/{userId}/address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Address> getAddresses() {
        List<Address> addresss = addressService.findAll();

        log.info("Retrieve objects {}", addresss);

        return addresss;
    }

/*    @RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, path = "/")
    public List<Address> getAddressesForUser(long userId) {
        List<Address> addresss = addressService.findAllForUser(userId);

        log.info("Addresses for user {}: {}", userId, addresss);

        return addresss;
    }*/

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Address save(@RequestBody Address address) {
        Address savedAddress = addressService.save(address);

        log.info("Added address {}", savedAddress);

        return savedAddress;
    }

    @GetMapping("/{id}")
    public Address find(@PathVariable Long id) {
        return addressService.find(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);

        log.info("Delete address with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public Address update(@RequestBody Address address) {
        Address updatedAddress = addressService.update(address);

        log.info("Updated Address {}", updatedAddress);

        return updatedAddress;
    }

    @Deprecated
    private void updateAddress(Address oldAddress, Address address) {
        oldAddress.setBuildingNumber(address.getBuildingNumber());
        oldAddress.setCode(address.getCode());
        oldAddress.setCity(address.getCity());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setLocalNumber(address.getLocalNumber());
        oldAddress.setUser(address.getUser());
    }
}
