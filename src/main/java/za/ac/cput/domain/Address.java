// Address.java
package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "customer")
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String postalCode;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}