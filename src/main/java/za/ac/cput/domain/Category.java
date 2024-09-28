package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;

    //@Lob
    //private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
    public void addProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        if (products != null) {
            products.remove(product);
            product.setCategory(null);
        }
    }

    public Category copy() {
        return this.toBuilder().build();
    }
    @PrePersist
    @PreUpdate
    private void ensureProductsSet() {
        if (this.products == null) {
            this.products = new HashSet<>();
        }
    }
}