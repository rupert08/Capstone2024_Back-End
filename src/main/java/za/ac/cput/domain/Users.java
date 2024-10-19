package za.ac.cput.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)  // Change to @SuperBuilder
@Entity
@Table
//@Inheritance
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected long userId;
    protected String username;
    protected String password;





}