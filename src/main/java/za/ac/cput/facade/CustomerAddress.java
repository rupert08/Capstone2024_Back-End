package za.ac.cput.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;
import za.ac.cput.service.CustomerService;

@Component
public class CustomerAddress {
    private AddressService addressService;
    private CustomerService customerService;

    @Autowired
    public CustomerAddress(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    public Address saveAddress(Address obj){
        if(customerService.read(obj.getCustomer().getUserId()) != null){
            return addressService.create(obj);
        }
        return null;
    }
}
