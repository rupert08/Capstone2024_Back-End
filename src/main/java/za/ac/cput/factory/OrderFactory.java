package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.domain.Shipping;
import za.ac.cput.util.Helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class OrderFactory {

    public static Order buildOrder(Cart cart, LocalDate orderDate, Shipping shipping, BigDecimal totalAmount, OrderStatus orderStatus) {
        if (Helper.isNullOrEmpty(String.valueOf(cart)) || Helper.isNullOrEmpty(String.valueOf(orderDate))
                || Helper.isNullOrEmpty(String.valueOf(shipping)) || Helper.isNullOrEmpty(String.valueOf(totalAmount))
                || Helper.isNullOrEmpty(String.valueOf(orderStatus))) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        return Order.builder()
                .cart(cart)
                .orderDate(orderDate)
                .shipping(shipping)
                .totalAmount(totalAmount.setScale(2, RoundingMode.HALF_UP))
                .orderStatus(OrderStatus.valueOf(String.valueOf(orderStatus)))
                .build();
    }
}