package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.ContactFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    private static Contact contact1 = ContactFactory.createContact("admin1@gmail.com", "0812345679");
    private static Admin admin1 = AdminFactory.createAdmin(contact1.getEmail(), "adminUser", contact1);

    @Test
    @Order(1)
    void a_create() {
        Admin created = adminService.create(admin1);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void b_read() {
        Admin read = adminService.read(admin1.getUserId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Disabled
    @Order(3)
    void c_delete() {
        adminService.delete(admin1.getUserId());
        System.out.println("Admin deleted where User ID: " + admin1.getUserId());
    }

    @Test
    @Order(4)
    void d_getAll() {
        System.out.println(adminService.getAll());
    }
}