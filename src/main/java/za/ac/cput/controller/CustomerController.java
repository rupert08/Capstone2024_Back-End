// CustomerController.java
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.CustomerService;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer obj) {
        Customer login = CustomerFactory.createCustomer(obj.getUsername(), obj.getPassword());

        if (login == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByUsernameAndPassword(login.getUsername(), login.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer obj) {
        Customer buildObj = CustomerFactory.createCustomer( obj.getFirstName(), obj.getLastName(), obj.getContact());
        Customer exists = customerService.read(obj.getUserId());
        if (buildObj == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        if (exists != null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerService.create(buildObj));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Customer> read(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.read(id));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long userId, @RequestBody Customer customer) {
        customer.setUserId(userId);
        Customer updatedCustomer = customerService.update(customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public Set<Customer> getAll() {
        return customerService.getAll();
    }
}