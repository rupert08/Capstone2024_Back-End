package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)  // Change to @SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Customer extends User implements Serializable {
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Address> address;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Contact contact;
}