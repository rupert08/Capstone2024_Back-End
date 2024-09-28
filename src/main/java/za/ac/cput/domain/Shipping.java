package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "shippings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Shipping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shippingID;

    @ManyToOne
    @JoinColumn(name = "deliveryAddress")
    private Address deliveryAddress;

    private OrderStatus orderStatus;
    private BigDecimal shippingFee;
}