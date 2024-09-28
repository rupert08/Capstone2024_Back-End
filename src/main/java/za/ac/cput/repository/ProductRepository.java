package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductsByName(String name);
}