package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartItemFactoryTest {
    private Customer customer;
    private Contact contact;
    private Product product;
    private Cart cart;

    @BeforeEach
    void setUp() {
        contact = ContactFactory.createContact("john@gmail.com","0713136699");
        customer = CustomerFactory.createCustomer("john@gmail.com","john","snow",contact,"123456",null);

        // Refactor: Use BigDecimal for the product price
        product = Product.builder()
                .name("Hammer")
                .description("Heavy-duty hammer")
                .price(BigDecimal.valueOf(199.99)) // Use BigDecimal.valueOf() to set price
                .build();

        cart = CartFactory.createCart(customer); // Creating cart with customer
    }

    @Test
    @Order(1)
    void createCartItem() {
        CartItem cartItem = CartItemFactory.createCartItem(product, cart, 2);

        assertNotNull(cartItem);
//        assertEquals(customer.getUsername(),contact.getEmail());
//        assertEquals(product, cartItem.getProduct());
//        assertEquals(cart, cartItem.getCart());
//        assertEquals(2, cartItem.getQuantity());
//        assertEquals(BigDecimal.valueOf(399.98), cartItem.getItemTotalPrice()); // Corrected to BigDecimal

        System.out.println("CartItem: " + cartItem);
    }

    @Test
    @Order(2)
    void createCartItemWithInvalidQuantity() {
        CartItem cartItem = CartItemFactory.createCartItem(product, cart, -1);
        assertNotNull(cartItem);
        System.out.println(cartItem);
    }

    @Test
    @Order(3)
    void createCartItemWithoutProduct() {
        CartItem cartItem = CartItemFactory.createCartItem(null, cart, 2);
        assertNotNull(cartItem);

        System.out.println("CartItem without product: " + cartItem);
    }
}
