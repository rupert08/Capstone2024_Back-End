package za.ac.cput.factory;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import za.ac.cput.domain.Order;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderFactoryTest {

    Contact contact = ContactFactory.createContact("john1@gmail.com", "0818945600");
    Customer customer = CustomerFactory.createCustomer(contact.getEmail(),"123456", "John", "Wick", contact);


    private Cart createValidCart() {
        Contact contact = ContactFactory.createContact("john@example.com", "0783139988");
        Customer customer = CustomerFactory.createCustomer(contact.getEmail(), "123456");
        return CartFactory.createCart(customer);
    }

    private Shipping createValidShipping() {
        Address address = AddressFactory.createAddress("21", "Main Street", "Capetown", "Western Cape", "8008", customer);
        return ShippingFactory.createShipping(address, OrderStatus.PENDING, BigDecimal.valueOf(50.0));
    }

    @Test
    void a_testBuildOrderWithAllFields() {
        Cart cart = createValidCart();
        Shipping shipping = createValidShipping();
        LocalDate orderDate = LocalDate.now();
        BigDecimal totalAmount = BigDecimal.valueOf(500.0);
        PaymentMethod paymentMethod = PaymentMethod.DEBIT_CARD;
        OrderStatus orderStatus = OrderStatus.PENDING;

        Order order =  OrderFactory.buildOrder(cart, orderDate, shipping, totalAmount,paymentMethod, orderStatus);
        assertNotNull(order);
        System.out.println(order);
    }

    @Test
    void b_testBuildOrderWithEmptyOrderItems() {
        Cart cart = createValidCart();
        Shipping shipping = createValidShipping();
        LocalDate orderDate = LocalDate.now();
        BigDecimal totalAmount = BigDecimal.valueOf(500.0);
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        OrderStatus orderStatus = null;

        Order order = OrderFactory.buildOrder(cart, orderDate, shipping, totalAmount,paymentMethod, orderStatus);
        assertNotNull(order);
        System.out.println(order);
    }
}