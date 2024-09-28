package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
