package za.ac.cput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import za.ac.cput.config.JWTService;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

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

    public String verify(Customer customer) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        customer.getUsername(),customer.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(customer.getUsername());
        }
        return "Failed to authenticate customer";
    }
}