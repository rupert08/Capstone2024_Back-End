package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemServiceTest {

    @Autowired
    CartItemService cartItemService;



    @Test
    void a_create() {

    }

    @Test
    void b_read() {
    }

    @Test
    void c_update() {
    }

    @Test
    void d_delete() {
    }

    @Test
    void e_getAll() {
    }
}