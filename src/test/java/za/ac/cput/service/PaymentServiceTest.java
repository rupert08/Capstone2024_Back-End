// PaymentServiceTest.java
package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        Contact contact = ContactFactory.createContact("", "");
        Customer customer = CustomerFactory.createCustomer("Customer", "Customer", " ", contact, "12345 ", Collections.emptyList());
        Cart cart = CartFactory.createCart(customer, Collections.emptyList());
        order = OrderFactory.buildOrder(cart, LocalDate.now(), null, BigDecimal.valueOf(200.0),PaymentMethod.DEBIT_CARD, OrderStatus.PENDING);
    }
    @Test
    void createPayment_success() {
        Payment payment = PaymentFactory.createPayment(order, LocalDate.now(), BigDecimal.valueOf(300.0), PaymentMethod.CREDIT_CARD);
        Payment savedPayment = paymentService.create(payment);
        assertNotNull(savedPayment);
        assertEquals(payment.getTotalPrice(), savedPayment.getTotalPrice());
    }

    @Test
    void readPayment_success() {
        Payment payment = PaymentFactory.createPayment(order, LocalDate.now(), BigDecimal.valueOf(300.0), PaymentMethod.CREDIT_CARD);
        Payment savedPayment = paymentService.create(payment);
        Payment readPayment = paymentService.read(savedPayment.getPaymentId());
        assertNotNull(readPayment);
        assertEquals(savedPayment.getPaymentId(), readPayment.getPaymentId());
    }

    @Test
    void updatePayment_success() {
        Payment payment = PaymentFactory.createPayment(order, LocalDate.now(), BigDecimal.valueOf(300.0), PaymentMethod.CREDIT_CARD);
        Payment savedPayment = paymentService.create(payment);
        savedPayment.setTotalPrice(BigDecimal.valueOf(350.0));
        Payment updatedPayment = paymentService.update(savedPayment);
        assertNotNull(updatedPayment);
        assertEquals(BigDecimal.valueOf(350.0), updatedPayment.getTotalPrice());
    }

    @Test
    void deletePayment_success() {
        Payment payment = PaymentFactory.createPayment(order, LocalDate.now(), BigDecimal.valueOf(300.0), PaymentMethod.CREDIT_CARD);
        Payment savedPayment = paymentService.create(payment);
        paymentService.delete(savedPayment.getPaymentId());
        Payment deletedPayment = paymentService.read(savedPayment.getPaymentId());
        assertNull(deletedPayment);
    }
}