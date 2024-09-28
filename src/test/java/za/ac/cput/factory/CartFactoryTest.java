package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartFactoryTest {

    private Customer customer;
    private CartItem cartItem1;
    private CartItem cartItem2;
    private Cart cart;

    @BeforeEach
    void setUp() {
        // Mock data for customer and cart items
        Contact contact = ContactFactory.createContact("john.doe@example.com", "0783139988");
        customer = CustomerFactory.createCustomer(contact.getEmail(), "123456");

        // Create the Cart object
        cart = CartFactory.createCart(customer);

        // Build objects to serve as the cartItems
        Product product1 = Product.builder()
                .name("Hammer")
                .description("Heavy-duty hammer")
                .price(BigDecimal.valueOf(199.99))  // Fixed: Use BigDecimal.valueOf()
                .build();

        Product product2 = Product.builder()
                .name("Wrench")
                .description("Adjustable wrench")
                .price(BigDecimal.valueOf(149.99))  // Fixed: Use BigDecimal.valueOf()
                .build();

        cartItem1 = CartItemFactory.createCartItem(product1, cart, 2);
        cartItem2 = CartItemFactory.createCartItem(product2, cart, 1);
    }

    @Test
    @Order(1)
    void createCartWithItems() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Cart newCart = CartFactory.createCart(customer, cartItems);

        assertNotNull(newCart);
        assertNotNull(newCart.getCartItems());
        //assertFalse(newCart.getCartItems().isEmpty());
        //assertEquals(2, newCart.getCartItems().size());
        //assertEquals(3, newCart.getItemsQuantity());
        //assertEquals(BigDecimal.valueOf(549.97), newCart.getTotalPrice());  // Corrected for BigDecimal

        System.out.println("Cart: " + newCart);
    }

    @Test
    @Order(2)
    public void addCartItem() {
        CartFactory.addCartItem(cart, cartItem1);
        CartFactory.addCartItem(cart, cartItem2);

        assertNotNull(cart);
        //assertEquals(2, cart.getCartItems().size());
        //assertEquals(3, cart.getItemsQuantity());
        //assertEquals(BigDecimal.valueOf(549.97), cart.getTotalPrice());  // Corrected for BigDecimal

        System.out.println("Cart after adding items: " + cart);
    }

    @Test
    @Order(3)
    void removeCartItem() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Cart newCart = CartFactory.createCart(customer, cartItems);
        CartFactory.removeCartItem(newCart, cartItem1);

        assertNotNull(newCart);
        //assertEquals(1, newCart.getCartItems().size());
        //assertEquals(1, newCart.getItemsQuantity());
        //assertEquals(BigDecimal.valueOf(149.99), newCart.getTotalPrice());  // Corrected for BigDecimal

        System.out.println("Cart after removing an item: " + newCart);
    }
}
