package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.service.interfaces.IAddressService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService {
    private AddressRepository addressRepository;

    @Autowired
    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address obj) {
        return addressRepository.save(obj);
    }

    @Override
    public Address read(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address update(Address obj) {
        return addressRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }


    @Override
    public Set<Address> getAllAddresses() {
        return addressRepository.findAll().stream().collect(Collectors.toSet());
    }
}
