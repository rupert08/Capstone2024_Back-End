package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.domain.Photo;
import za.ac.cput.domain.Product;

import java.math.BigDecimal;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PhotoFactoryTest {

    public PhotoFactoryTest() throws Exception {
    }

    @Test
    void a_createPhoto() throws Exception {
        Category category = CategoryFactory.createCategoryWithoutProducts("Power Tools", "Dummy Description");
        Product product = ProductFactory.createProduct("Chain Saw", "Dummy Description", BigDecimal.valueOf(699.99), category );
        Photo photo = PhotoFactory.createPhoto(product, "image", product.getName(), "jpg");
        System.out.println(photo);
    }
}
