package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}