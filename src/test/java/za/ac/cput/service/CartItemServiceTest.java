package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // Add this line to maintain state across tests
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    private Cart cart;
    private Product product;
    private CartItem cartItem;
    private CartItem savedCartItem;

    @BeforeEach
    void setUp() throws Exception {
        // Generate unique email for customer
        String uniqueEmail = "john.doe+" + System.currentTimeMillis() + "@example.com";
        Contact contact = ContactFactory.createContact(uniqueEmail, "0211234567");

        Customer customer = CustomerFactory.createCustomer(
                uniqueEmail, "John", "Doe", contact, "password123", null);
        Customer savedCustomer = customerService.create(customer);

        cart = CartFactory.createCart(savedCustomer);
        Category category = CategoryFactory.createCategoryWithoutProducts("Hand Tools", "Various tools");
        product = ProductFactory.createProduct("Hammer", "Heavy-duty hammer", BigDecimal.valueOf(19.99), category);

        cartItem = CartItemFactory.createCartItem(product, cart, 2);
    }

    @Test
    @Order(1)
    void a_create() {
        // Save cart and product
        Cart savedCart = cartService.create(cart);
        Product savedProduct = productService.create(product);

        cartItem.setCart(savedCart);
        cartItem.setProduct(savedProduct);

        savedCartItem = cartItemService.create(cartItem);  // Store the saved CartItem with the ID

        // Now assert that savedCartItem is not null
        assertNotNull(savedCartItem, "Created CartItem should not be null");
        assertNotNull(savedCartItem.getCartItemId(), "CartItem ID should not be null after saving");

        System.out.println("Created cartItem: " + savedCartItem);
    }


    @Test
    @Order(2)
    void b_read() {
        // Read the cartItem by ID (use the ID returned from the create() test)
        CartItem readCartItem = cartItemService.read(savedCartItem.getCartItemId());

        // Make sure it's not null
        assertNotNull(readCartItem, "Expected CartItem should not be null");

        System.out.println("Read CartItem: " + readCartItem);  // Log read item
        assertEquals(savedCartItem.getCartItemId(), readCartItem.getCartItemId());
    }

    @Test
    @Order(3)
    void c_update() {
        // Ensure the savedCartItem is not null
        assertNotNull(savedCartItem, "Saved CartItem should not be null from create test");

        // Update the quantity of the cartItem
        savedCartItem.setQuantity(3);
        CartItem updatedCartItem = cartItemService.update(savedCartItem);
        assertNotNull(updatedCartItem, "Updated CartItem should not be null");
        assertEquals(3, updatedCartItem.getQuantity(), "Updated CartItem quantity should be 3");
    }

    @Test
    @Order(4)
    void d_delete() {
        // Ensure the savedCartItem is not null
        assertNotNull(savedCartItem, "Saved CartItem should not be null from create test");

        // Delete the cartItem and verify it's removed
        cartItemService.delete(savedCartItem.getCartItemId());
        CartItem deletedCartItem = cartItemService.read(savedCartItem.getCartItemId());
        assertNull(deletedCartItem, "Deleted CartItem should be null");
    }
}
