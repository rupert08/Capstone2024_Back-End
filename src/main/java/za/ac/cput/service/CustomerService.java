package za.ac.cput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.interfaces.ICustomerService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  // Lombok will generate the constructor for final fields(Autowired)
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer read(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Set<Customer> getAll() {
        return customerRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Customer findByUsernameAndPassword(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }

//    public Customer partialUpdate(Customer customer) {
//        Optional<Customer> existingCustomerOptional = customerRepository.findById(customer.getUserId());
//        if (existingCustomerOptional.isPresent()) {
//            Customer existingCustomer = existingCustomerOptional.get();
//
//            Customer.CustomerBuilder builder = existingCustomer.toBuilder();
//
//            if (customer.getFirstName() != null) {
//                builder.firstName(customer.getFirstName());
//            }
//            if (customer.getLastName() != null) {
//                builder.lastName(customer.getLastName());
//            }
//            if (customer.getUsername() != null) {
//                builder.username(customer.getUsername());
//            }
//            if (customer.getPassword() != null) {
//                builder.password(customer.getPassword());
//            }
//            if (customer.getContact() != null) {
//                Contact existingContact = existingCustomer.getContact();
//                Contact newContact = customer.getContact();
//                Contact.ContactBuilder contactBuilder = existingContact.toBuilder();
//
//                if (newContact.getEmail() != null) {
//                    contactBuilder.email(newContact.getEmail());
//                }
//                if (newContact.getPhoneNumber() != null) {
//                    contactBuilder.phoneNumber(newContact.getPhoneNumber());
//                }
//
//                builder.contact(contactBuilder.build());
//            }
//            if (customer.getAddress() != null) {
//                List<Address> existingAddresses = (List<Address>) existingCustomer.getAddress();
//                List<Address> newAddresses = (List<Address>) customer.getAddress();
//                builder.address((List<Address>) (newAddresses != null ? newAddresses : existingAddresses));
//            }
//
//            return customerRepository.save(builder.build());
//        }
//        return null;
//    }
}
