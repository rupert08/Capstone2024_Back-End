package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
