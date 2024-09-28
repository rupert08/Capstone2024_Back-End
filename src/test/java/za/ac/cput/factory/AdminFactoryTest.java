package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AdminFactoryTest {

    @Test
    @Order(1)
    void a_createAdmin() {
        Contact contact = ContactFactory.createContact("matt@gmail.com", "0812345679");
        assertNotNull(contact);
        Admin admin = AdminFactory.createAdmin(contact.getEmail(), "123456",contact);

        assertNotNull(admin);
        System.out.println(admin);
    }
    @Test
    @Order(2)
    void b_createAdmin_Failed() {
        Contact contact = ContactFactory.createContact("invalid.com", "0218945612");
        assertNotNull(contact);
        Admin admin = AdminFactory.createAdmin(contact.getEmail(), "123456", contact);

        assertNotNull(admin);
        System.out.println(admin);
    }
    }
