package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.CategoryFactory;
import za.ac.cput.factory.ProductFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    //private static Blob imageBlob;
    private static Category category;
    private static Product product;
    static {
        try {
            category = CategoryFactory.createCategoryWithoutProducts("Hand Tools", "Tools for home improvement");
            product = ProductFactory.createProduct("Drill", "Powerful drilling machine", BigDecimal.valueOf(199.99), category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    void a_create() {
        Product created = productService.create(product);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void b_read() {
        Product read = productService.read(product.getProductId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    @Disabled
    void c_delete() {
        productService.delete(product.getProductId());
        System.out.println("Product deleted where Product ID: " + product.getProductId());
    }

    @Test
    @Order(4)
    void d_getAll() {
        productService.getAll();
        System.out.println(productService.getAll());
    }
}