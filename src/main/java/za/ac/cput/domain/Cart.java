package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "cartItems")
@EqualsAndHashCode(exclude = "cartItems")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<CartItem> cartItems = new HashSet<>();

    public void addItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
        calculateTotalPrice();
    }

    public void removeItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setCart(null);
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        BigDecimal total = cartItems.stream()
                .map(CartItem::getItemTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // Assuming you have a totalPrice field
        // this.totalPrice = total.setScale(2, RoundingMode.HALF_UP);
    }

    public int getItemsQuantity() {
        return cartItems.size();
    }

    public BigDecimal getTotalPrice() {
        return cartItems.stream()
                .map(CartItem::getItemTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }
}