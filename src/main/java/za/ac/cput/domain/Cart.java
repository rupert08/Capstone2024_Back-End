// Cart.java
package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
//add comments to the class

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToOne
    @JoinColumn(name = "userID")
    private Customer customer;

    private int itemsQuantity;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    //floats values should be giving values with 2 decimal places
    @Column(columnDefinition = "DECIMAL(7,2)")
    private BigDecimal totalPrice;

    // Add item to cart
    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
        updateItemsQuantity();
        calculateTotalPrice();
    }

    // Remove item from cart
    public void removeItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
        updateItemsQuantity();
        calculateTotalPrice();
    }
//add comments in the class

    // Recalculate total price as a float

    public void calculateTotalPrice() {
        totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private void updateItemsQuantity() {
        itemsQuantity = cartItems.size();
    }
}