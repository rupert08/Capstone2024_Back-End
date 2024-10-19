// CustomerFactoryTest.java
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerFactoryTest {
    List<Address> addresses;
    Contact contact = ContactFactory.createContact("Wick1@gmail.com", "0818945600");
    Address address = AddressFactory.createAddress("12", "Test Street", "Grabouw", "Western Cape", "7160");

    Customer customer = CustomerFactory.createCustomer(contact.getEmail(), "John", "Wick", contact, "123456", addresses);

    @Test
    @Order(1)
    void a_customerLoginDetails_success() {
        Customer customer1 = CustomerFactory.createCustomer(customer.getUsername(), customer.getPassword());
        assertEquals(customer.getUsername(), customer1.getUsername());
        assertNotNull(customer1);
        System.out.println(customer1);
    }

    @Test
    @Order(2)
    void b_customerLoginDetails_failed() {
        Customer customer2 = CustomerFactory.createCustomer("fakeMail.com", "12345" );
        assertNotNull(customer2);
        System.out.println(customer2);
    }

    @Test
    @Order(3)
    void c_customerRegisterDetails_Success() {
        Address address = AddressFactory.createAddress("12", "Test Street", "Grabouw", "Western Cape", "7160");
        assertNotNull(address);
        Customer customer = CustomerFactory.createCustomer(contact.getEmail(), "John", "Wick", contact, "123456", Collections.singletonList(address));

        assertNotNull(customer);
        System.out.println(customer);
    }

    @Test
    @Order(4)
    void d_customerRegisterDetails_failed() {
        Address addressF = AddressFactory.createAddress("12", "Test Street", "Grabouw", "Western Cape", "");
        Customer customer3 = CustomerFactory.createCustomer(contact.getEmail(), "John", "Moses", contact, "123456", Collections.singletonList(address));

        assertNotNull(customer3);
        System.out.println(customer3);
    }
}