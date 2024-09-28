// CartItem.java
package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "cart")
@EqualsAndHashCode
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private int quantity;

    private  BigDecimal itemTotalPrice;

    // Calculate item total price,scale to 2 decimal places

    public void calculateItemTotalPrice() {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (product != null) {
            itemTotalPrice = product.getPrice()
                    .multiply(BigDecimal.valueOf(quantity))
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            itemTotalPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal getPrice() {
        return itemTotalPrice;
    }
}