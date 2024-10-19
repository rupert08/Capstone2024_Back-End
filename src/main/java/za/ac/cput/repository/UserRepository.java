package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
}
