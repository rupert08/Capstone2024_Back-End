// Product.java
package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode(exclude = "category")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column //(nullable = false)
    private String name;

    private String description;


    @Column(/*nullable = false,*/ columnDefinition = "DECIMAL(7,2)")
    // Changed from float to BigDecimal to make sure there is 2 decimal places
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;

    private String imageName;
    private String imageType;

    @Column(length = 65555)
    @Lob
    private byte[] imageData;


}