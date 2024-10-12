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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartServiceTest {

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
        Cart savedCart = cartService.create(cart);
        Product savedProduct = productService.create(product);

        cartItem.setCart(savedCart);
        cartItem.setProduct(savedProduct);

        savedCartItem = cartItemService.create(cartItem);

        assertNotNull(savedCartItem, "Created CartItem should not be null");
        assertNotNull(savedCartItem.getCartItemId(), "CartItem ID should not be null after saving");

        System.out.println("Created cartItem: " + savedCartItem);
    }

    @Test
    @Order(2)
    void b_addCartItemToCart() {
        CartItem cartItem = CartItem.builder()
                .quantity(3)
                .itemTotalPrice(new BigDecimal("39.99"))
                .product(product)
                .build();

        cartService.addCartItemToCart(cart, cartItem);

        assertEquals(1, cart.getItemsQuantity());
        assertEquals(new BigDecimal("39.99"), cart.getTotalPrice());
    }

    @Test
    @Order(3)
    void c_removeCartItemFromCart() {
        CartItem cartItem = CartItem.builder()
                .quantity(3)
                .itemTotalPrice(new BigDecimal("39.99"))
                .product(product)
                .build();

        cartService.addCartItemToCart(cart, cartItem);
        cartService.removeCartItemFromCart(cart, cartItem);

        assertEquals(0, cart.getItemsQuantity());
        assertEquals(new BigDecimal("0.00"), cart.getTotalPrice());
    }
}