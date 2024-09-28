package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Customer;
import za.ac.cput.service.interfaces.IService;

import java.util.Set;

public interface ICustomerService extends IService<Customer, Long> {
    Set<Customer> getAll();

    Customer findByUsernameAndPassword(String username, String password);
}
