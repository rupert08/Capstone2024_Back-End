package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.AdminService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Admin obj) {
        Admin admin = AdminFactory.adminLogin(obj.getUsername(), obj.getPassword());

        if (admin == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("WWW-Authenticate", "None");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(Map.of("error", "Invalid email or password."));
        }

        String token = adminService.verify(admin);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", "admin");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}