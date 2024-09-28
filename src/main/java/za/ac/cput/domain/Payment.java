package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId")
    private Order order;

    private LocalDate paymentDate;
    private BigDecimal totalPrice ;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
}