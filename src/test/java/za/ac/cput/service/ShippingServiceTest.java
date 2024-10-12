// ShippingServiceTest.java
package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.domain.Shipping;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ShippingFactory;
import za.ac.cput.repository.ShippingRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShippingServiceTest {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private ShippingRepository shippingRepository;

    private static final Address address = AddressFactory.createAddress("123", "Main Street", "Cape Town", "Western Cape", "8000");

    @Test
    void createShipping_success() {
        Shipping shipping = ShippingFactory.createShipping(address, OrderStatus.PENDING, BigDecimal.valueOf(50.0));
        Shipping savedShipping = shippingService.create(shipping);
        assertNotNull(savedShipping);
        assertEquals(shipping.getShippingFee(), savedShipping.getShippingFee());
    }

    @Test
    void readShipping_success() {
        Shipping shipping = ShippingFactory.createShipping(address, OrderStatus.PENDING, BigDecimal.valueOf(50.0));
        Shipping savedShipping = shippingService.create(shipping);
        Shipping readShipping = shippingService.read(savedShipping.getShippingID());
        assertNotNull(readShipping);
        assertEquals(savedShipping.getShippingID(), readShipping.getShippingID());
    }

    @Test
    void updateShipping_success() {
        Shipping shipping = ShippingFactory.createShipping(address, OrderStatus.PENDING, BigDecimal.valueOf(50.0));
        Shipping savedShipping = shippingService.create(shipping);
        savedShipping.setShippingFee(BigDecimal.valueOf(60.0));
        Shipping updatedShipping = shippingService.update(savedShipping);
        assertNotNull(updatedShipping);
        assertEquals(BigDecimal.valueOf(60.0), updatedShipping.getShippingFee());
    }

    @Test
    void deleteShipping_success() {
        Shipping shipping = ShippingFactory.createShipping(address, OrderStatus.PENDING, BigDecimal.valueOf(50.0));
        Shipping savedShipping = shippingService.create(shipping);
        shippingService.delete(savedShipping.getShippingID());
        Shipping deletedShipping = shippingService.read(savedShipping.getShippingID());
        assertNull(deletedShipping);
    }
}