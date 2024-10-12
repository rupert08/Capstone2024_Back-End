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
@ToString(exclude = {"product", "cart"})
@EqualsAndHashCode(exclude = {"product", "cart"})
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private int quantity;

    @Builder.Default
    private BigDecimal itemTotalPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

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
}