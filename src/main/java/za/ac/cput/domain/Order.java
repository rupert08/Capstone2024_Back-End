// Order.java
package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static za.ac.cput.domain.PaymentMethod.CREDIT_CARD;
import static za.ac.cput.domain.PaymentMethod.DEBIT_CARD;

@SuppressWarnings({"ALL", "JpaAttributeTypeInspection"})
@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @OneToOne
    @JoinColumn(name = "cartID")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "shippingID")
    private Shipping shipping;

    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private Payment paymentMethod;
    private OrderStatus orderStatus;
    private boolean shippedOrCollected;

    public void calculateTotalAmount() {
        if (cart != null) {
            totalAmount = cart.getTotalPrice();
        } else {
            totalAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }
}