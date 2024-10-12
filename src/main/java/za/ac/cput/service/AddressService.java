package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.interfaces.IAddressService;

import java.util.Set;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Address create(Address address) {
        // Ensure the Customer entity is managed
        Customer customer = address.getCustomer();
        if (customer != null) {
            customer = customerRepository.findById(customer.getUserId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            address.setCustomer(customer);
        }
        return addressRepository.save(address);
    }

    @Override
    public Address read(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Set<Address> getAllAddresses() {
        return Set.of();
    }
}




