package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long photoId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    private String image;

    private String name;

    private String photoType; //Had to add 4th attr in order to create facade with needed file values
}
