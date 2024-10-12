package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.*;
import za.ac.cput.service.AddressService;
import za.ac.cput.service.CartService;
import za.ac.cput.service.CustomerService;
import za.ac.cput.service.ShippingService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:5119/ecommerce/order";

    private static Order savedOrder;
    private static Cart savedCart;
    private static Shipping savedShipping;

    @BeforeAll
    static void setUp(@Autowired CartService cartService, @Autowired ShippingService shippingService, @Autowired CustomerService customerService, @Autowired AddressService addressService) {
        // Create and save a Customer
        Contact contact = ContactFactory.createContact("john.doe@example.com", "0211234567");
        Customer customer = CustomerFactory.createCustomer(contact.getEmail(), "password", "John", "Doe", contact);
        Customer savedCustomer = customerService.create(customer);

        // Create and save a Cart
        Cart cart = CartFactory.createCart(savedCustomer);
        savedCart = cartService.create(cart);

        // Create and save an Address
        Address address = AddressFactory.createAddress("123", "Main St", "City", "State", "12345", savedCustomer);
        Address savedAddress = addressService.create(address);

        // Create and save Shipping
        Shipping shipping = ShippingFactory.createShipping(savedAddress, OrderStatus.PENDING, BigDecimal.valueOf(50.00));
        savedShipping = shippingService.create(shipping);
    }


    @Test
    @org.junit.jupiter.api.Order(1)
    void a_create() {
        Order order = OrderFactory.buildOrder(savedCart, LocalDate.now(), savedShipping, BigDecimal.valueOf(100.00),PaymentMethod.CREDIT_CARD, OrderStatus.PENDING);
        ResponseEntity<Order> response = restTemplate.postForEntity(BASE_URL + "/create", order, Order.class);
        assertNotNull(response.getBody());
        savedOrder = response.getBody();
    }


    @Test
    @org.junit.jupiter.api.Order(2)
    void b_read() {
        ResponseEntity<Order> response = restTemplate.getForEntity(BASE_URL + "/read/" + savedOrder.getOrderID(), Order.class);
        assertNotNull(response.getBody(), "Expected Order should not be null");
        assertEquals(savedOrder.getOrderID(), response.getBody().getOrderID());
        System.out.println("Read Order: " + response.getBody());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void c_update() {
        savedOrder.setTotalAmount(BigDecimal.valueOf(250.0));
        savedOrder.setOrderStatus(OrderStatus.SHIPPED);
        ResponseEntity<Order> response = restTemplate.postForEntity(BASE_URL + "/update", savedOrder, Order.class);

        assertNotNull(response.getBody(), "Updated Order should not be null");
       // assertEquals(BigDecimal.valueOf(250.0), response.getBody().getTotalAmount(), "Updated Order total amount should be 250.0");
        System.out.println("Updated Order: " + response.getBody());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @Disabled
    void d_delete() {
        restTemplate.delete(BASE_URL + "/delete/" + savedOrder.getOrderID());
        ResponseEntity<Order> response = restTemplate.getForEntity(BASE_URL + "/read/" + savedOrder.getOrderID(), Order.class);
        assertNull(response.getBody(), "Deleted Order should be null");
    }
}