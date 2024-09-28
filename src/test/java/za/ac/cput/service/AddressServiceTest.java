package za.ac.cput.service;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @Autowired
    AddressService addressService;

    private static final Contact contact = ContactFactory.createContact("test6@example.com", "0783139988");
    private static final Customer customer = CustomerFactory.createCustomer(contact.getEmail(), "123456","John", "Wick", contact );
    private static final Address address = AddressFactory.createAddress("21", "Main Street", "Cape Town", "Western Cape", "8008");


    @Test
    void create() {
        Address created = addressService.create(address);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void read() {
        Address read = addressService.read(address.getAddressId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        Address newAddress = address.toBuilder()
                .streetName("Changed Street")
                .build();
        Address updated = addressService.update(newAddress);
        assertNotNull(updated);
        System.out.println(updated);
    }
}