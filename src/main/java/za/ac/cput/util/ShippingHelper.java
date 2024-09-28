package za.ac.cput.util;

import za.ac.cput.domain.OrderStatus;

public class ShippingHelper {
    public static boolean isValidShippingFee(float shippingFee) {
        return shippingFee >= 0;
    }

    public static boolean isValidOrderStatus(String orderStatus) {
        try {
            OrderStatus.valueOf(orderStatus);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void checkValidShippingFee(float shippingFee) {
        if (!isValidShippingFee(shippingFee)) {
            throw new RuntimeException("Invalid shipping fee");
        }
    }

    public static void checkValidOrderStatus(String orderStatus) {
        if (!isValidOrderStatus(orderStatus)) {
            throw new RuntimeException("Invalid order status");
        }
    }
}