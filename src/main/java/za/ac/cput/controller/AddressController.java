package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Category;
import za.ac.cput.facade.CustomerAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.service.AddressService;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    private CustomerAddress customerAddress;

    @Autowired
    public AddressController(AddressService addressService, CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<Address> create(@RequestBody Address obj) {
        Address address = AddressFactory.createAddress(obj.getStreetNumber(), obj.getStreetName(), obj.getCity(), obj.getState(), obj.getPostalCode(), obj.getCustomer());
        if(address == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }

        Address request = customerAddress.saveAddress(address);

        if(request == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Address> read(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.read(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Address> update(@RequestBody Address obj) {
        Address address = AddressFactory.createAddress(obj.getStreetNumber(), obj.getStreetName(), obj.getCity(), obj.getState(), obj.getPostalCode(), obj.getCustomer());
        if(address == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(address));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        addressService.delete(id);
    }

    @GetMapping("/getAll")
    public Set<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

}
