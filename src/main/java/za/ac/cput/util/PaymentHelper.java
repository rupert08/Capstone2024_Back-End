package za.ac.cput.util;

import za.ac.cput.domain.PaymentMethod;

import java.time.LocalDate;

public class PaymentHelper {
    public static boolean isValidPaymentDate(LocalDate paymentDate) {
        return paymentDate != null && !paymentDate.isAfter(LocalDate.now());
    }

    public static boolean isValidTotalPrice(float totalPrice) {
        return totalPrice >= 0;
    }

    public static boolean isValidPaymentMethod(String method) {
        try {
            PaymentMethod.valueOf(method);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void checkValidPaymentDate(LocalDate paymentDate) {
        if (!isValidPaymentDate(paymentDate)) {
            throw new RuntimeException("Invalid payment date");
        }
    }

    public static void checkValidTotalPrice(float totalPrice) {
        if (!isValidTotalPrice(totalPrice)) {
            throw new RuntimeException("Invalid total price");
        }
    }

    public static void checkValidPaymentMethod(String method) {
        if (!isValidPaymentMethod(method)) {
            throw new RuntimeException("Invalid payment method");
        }
    }
}