package za.ac.cput.service;

//import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.CustomerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class  CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    private static final Contact contact = ContactFactory.createContact("connor1@gmail.com", "02189456123");
    private static final Address address = AddressFactory.createAddress("12", "Test Street", "Grabouw", "Western Cape", "7160");

    private static final Customer customer = CustomerFactory.createCustomer(
            contact.getEmail(), "Connor", "McGregor", contact, "123456", Collections.singletonList(address));;

    @Test
    void a_create() {
        address.setCustomer(customer);
        Customer created = customerService.create(customer);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Customer read = customerService.read(customer.getUserId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Disabled
    void c_update() {
        Customer newCustomer = customer.toBuilder()
                .firstName("Johnathan")
                .build();
        Customer updated = customerService.update(newCustomer);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Disabled
    void d_delete() {
        customerService.delete(customer.getUserId());
        System.out.println("Customer Deleted Where User ID = " + customer.getUserId());
    }

    @Test
    void e_getAll() {
        System.out.println(customerService.getAll());
    }
}
