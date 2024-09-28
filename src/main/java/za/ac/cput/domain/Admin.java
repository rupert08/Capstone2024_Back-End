package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)  // Use @SuperBuilder instead of @Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Admin extends User implements Serializable {

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contactId")
    private Contact contact;

}