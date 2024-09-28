package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Photo;
import za.ac.cput.domain.Product;

import java.util.Set;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
