//package za.ac.cput.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import za.ac.cput.domain.Admin;
//import za.ac.cput.repository.AdminRepository;
//
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5116", maxAge = 3600)
//@RequestMapping("/api")
//public class AuthController {
//
//    private final AdminRepository adminRepository;
//
//    public AuthController(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Admin admin) {
//        Admin foundAdmin = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
//        if (foundAdmin != null) {
//            return ResponseEntity.ok(foundAdmin);
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
//@PostMapping("/logout")
//    public ResponseEntity<?> logout () {
//        return ResponseEntity.ok("successfully logged out");
//    }
//
//
//
//}
