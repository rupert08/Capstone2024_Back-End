package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.domain.Shipping;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShippingFactoryTest {
    private Shipping shipping;

    @Test
    @Order(1)
    void createShipping_success() {
        Address address = Address.builder()
                .streetNumber("123")
                .streetName("Main Street")
                .city("Cape Town")
                .state("Western Cape")
                .postalCode("8000")
                .build();
        OrderStatus orderStatus = OrderStatus.PENDING;
        BigDecimal shippingFee = BigDecimal.valueOf(50.0); // Use BigDecimal for shipping fee

        shipping = ShippingFactory.createShipping(address, orderStatus, shippingFee);

        assertNotNull(shipping);
        assertEquals(address, shipping.getDeliveryAddress());
        assertEquals(orderStatus, shipping.getOrderStatus());
        //assertEquals(shippingFee, shipping.getShippingFee());
        System.out.println(shipping);
    }

    @Test
    @Order(2)
    void createShipping_invalidShippingFee() {
        Address address = Address.builder()
                .streetNumber("123")
                .streetName("Main Street")
                .city("Cape Town")
                .state("Western Cape")
                .postalCode("8000")
                .build();
        OrderStatus orderStatus = OrderStatus.PENDING;
        BigDecimal shippingFee = BigDecimal.valueOf(-10.0); // Invalid shipping fee

        // Uncomment to test exception handling
        // assertThrows(RuntimeException.class, () -> {
        //     ShippingFactory.createShipping(address, orderStatus, shippingFee);
        // });

        shipping = ShippingFactory.createShipping(address, orderStatus, shippingFee);
        System.out.println(shipping);
    }

    @Test
    @Order(3)
    void createShipping_nullAddress() {
        Address address = null; // Invalid address
        OrderStatus orderStatus = OrderStatus.PENDING;
        BigDecimal shippingFee = BigDecimal.valueOf(50.0);

        // Uncomment to test exception handling
        // assertThrows(RuntimeException.class, () -> {
        //     ShippingFactory.createShipping(null, orderStatus, shippingFee);
        // });

        shipping = ShippingFactory.createShipping(address, orderStatus, shippingFee);
        assertNotNull(shipping);
        System.out.println(shipping);
    }

    @Test
    @Order(4)
    void createShipping_invalidOrderStatus() {
        Address address = Address.builder()
                .streetNumber("123")
                .streetName("Main Street")
                .city("Cape Town")
                .state("Western Cape")
                .postalCode("8000")
                .build();
        OrderStatus orderStatus = null; // Invalid order status
        BigDecimal shippingFee = BigDecimal.valueOf(50.0);

        // Uncomment to test exception handling
        // assertThrows(RuntimeException.class, () -> {
        //     ShippingFactory.createShipping(address, orderStatus, shippingFee);
        // });

        shipping = ShippingFactory.createShipping(address, orderStatus, shippingFee);
        System.out.println(shipping);
    }
}
