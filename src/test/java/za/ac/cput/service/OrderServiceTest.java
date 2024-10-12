package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    private Cart cart;
    private Shipping shipping;
    private za.ac.cput.domain.Order order;
    private za.ac.cput.domain.Order savedOrder;

    @BeforeAll
    void setUp() throws Exception {
        String uniqueEmail = "john.doe+@example.com";
        Contact contact = ContactFactory.createContact(uniqueEmail, "0211234567");

        Customer customer = CustomerFactory.createCustomer(
                contact.getEmail(), "12345", "John", "Wick", contact);
        Customer savedCustomer = customerService.create(customer);

        assertNotNull(savedCustomer, "Saved Customer should not be null");

        cart = CartFactory.createCart(savedCustomer);
        Cart savedCart = cartService.create(cart);

        Address address = AddressFactory.createAddress("123", "Main St", "City", "State", "12345", savedCustomer);
        Address savedAddress = addressService.create(address);

        shipping = ShippingFactory.createShipping(
                savedAddress, OrderStatus.PENDING, BigDecimal.valueOf(50.00));
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void a_create() {
        order = OrderFactory.buildOrder(cart, LocalDate.now(), shipping, BigDecimal.valueOf(100.00),PaymentMethod.DEBIT_CARD, OrderStatus.PENDING);
        savedOrder = orderService.create(order);

        assertNotNull(savedOrder, "Created Order should not be null");
        assertNotNull(savedOrder.getOrderID(), "Order ID should not be null after saving");

        System.out.println("Created order: " + savedOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void b_read() {
        Order readOrder = orderService.read(order.getOrderID());
        assertNotNull(readOrder, "Expected Order should not be null");
        assertEquals(order.getOrderID(), readOrder.getOrderID());
        System.out.println("Read Order: " + readOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
//    @Disabled
    void c_update() {
        savedOrder.setTotalAmount(BigDecimal.valueOf(250.0));
        savedOrder.setOrderStatus(OrderStatus.SHIPPED);
        Order updatedOrder = orderService.update(savedOrder);
        assertNotNull(updatedOrder, "Updated Order should not be null");
        assertEquals(BigDecimal.valueOf(250.0), updatedOrder.getTotalAmount(), "Updated Order total amount should be 250.0");
        System.out.println("Updated Order: " + updatedOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @Disabled
    void d_delete() {
        orderService.delete(savedOrder.getOrderID());
        za.ac.cput.domain.Order deletedOrder = orderService.read(savedOrder.getOrderID());
        assertNull(deletedOrder, "Deleted Order should be null");
    }
    @Test
    @org.junit.jupiter.api.Order(5)
    void d_getAll() {
        List<Order> orders = orderService.getAll();
        assertFalse(orders.isEmpty());
        System.out.println(orders);

    }
}