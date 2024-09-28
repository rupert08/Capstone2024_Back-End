package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.util.Helper;
import za.ac.cput.util.PaymentHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class PaymentFactory {
    public static Payment createPayment(Order order, LocalDate paymentDate, BigDecimal totalPrice, PaymentMethod method) {
        Helper.checkNullParam(order);
        PaymentHelper.checkValidPaymentDate(paymentDate);
        PaymentHelper.checkValidTotalPrice(totalPrice.floatValue());
        PaymentHelper.checkValidPaymentMethod(method.name());

        return Payment.builder()
                .order(order)
                .paymentDate(paymentDate)
                .totalPrice(totalPrice.setScale(2, RoundingMode.HALF_UP))
                .method(method)
                .build();
    }
}