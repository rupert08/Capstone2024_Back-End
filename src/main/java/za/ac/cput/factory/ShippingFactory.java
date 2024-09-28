package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.domain.Shipping;
import za.ac.cput.util.Helper;
import za.ac.cput.util.ShippingHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShippingFactory {
    public static Shipping createShipping(Address deliveryAddress, OrderStatus orderStatus, BigDecimal shippingFee) {
        if (Helper.checkNullParam(deliveryAddress)) {
            throw new RuntimeException("Delivery address is invalid");
        }
        if (Helper.checkNullParam(orderStatus)) {
            throw new RuntimeException("Order status is invalid");
        }
        ShippingHelper.checkValidOrderStatus(orderStatus.name());
        ShippingHelper.checkValidShippingFee(shippingFee.floatValue());

        return Shipping.builder()
                .deliveryAddress(deliveryAddress)
                .orderStatus(orderStatus)
                .shippingFee(shippingFee.setScale(2, RoundingMode.HALF_UP))
                .build();
    }
}