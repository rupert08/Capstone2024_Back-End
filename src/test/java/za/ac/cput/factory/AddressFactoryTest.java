package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressFactoryTest {

    private Customer customer;
    private Contact contact;
    private Address address;
    @Test
    @Order(1)
    void a_createStandaloneAddress_success() {
        contact = ContactFactory.createContact("john.doe@example.com", "0783139988");
        customer = CustomerFactory.createCustomer(contact.getEmail(), "password");
        address = AddressFactory.createAddress("21","Main Street", "Capetown", "Western Cape", "8008", customer);
        assertNotNull(address);
        System.out.println(address);
    }

    @Test
    @Order(2)
    void b_createApartmentAddress_success() {
        Address address = AddressFactory.createAddress("2",  "Main Street", "Capetown", "Western Cape", "8008", customer);
        assertNotNull(address);
        System.out.println(address);
    }

    @Test
    @Order(3)
    void c_createUnitAddress_success() {
        Address address = AddressFactory.createAddress("5", "Main Street", "Capetown", "Western Cape", "8008", customer);
        assertNotNull(address);
        System.out.println(address);
    }

    @Test
    @Order(4)
    void d_createAddress_failed_missingStreetNumber() {
        Address address = AddressFactory.createAddress("", "Main Street", "Capetown", "Western Cape", "8008", customer);
        assertNotNull(address);
        System.out.println(address);
    }

    @Test
    @Order(5)
    void e_createAddress_failed_invalidPostalCode() {
        Address address = AddressFactory.createAddress("21","Main Street", "Capetown", "Western Cape", "ABCD", customer);
        assertNotNull(address);
        System.out.println(address);
    }

    @Test
    @Order(6)
    void f_createAddress_failed_missingEssentialFields() {
        Address address = AddressFactory.createAddress("",  "Capetown", "Western Cape", "8008", String.valueOf(customer));
        assertNotNull(address);
        System.out.println(address.toString());
    }

    @Test
    @Order(7)
    void g_createAddress_failed_invalidApartmentCombination() {
        Address address = AddressFactory.createAddress("", "Main Street", "Capetown", "Western Cape", "8008", customer);
        assertNotNull(address);
        System.out.println(address.toString());
    }

    @Test
    @Order(8)
    void h_createAddress_failed_invalidUnitCombination() {
        Address address = AddressFactory.createAddress("", "Main Street", "Capetown", "Western Cape", "8008", customer);
        assertNotNull(address);
        System.out.println(address.toString());
    }

}