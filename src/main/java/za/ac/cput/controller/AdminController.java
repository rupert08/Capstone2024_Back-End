package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.service.AdminService;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin obj) {
        Admin login = AdminFactory.adminLogin(obj.getUsername(), obj.getPassword());

        if (login == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByUsernameAndPassword(login.getUsername(), login.getPassword()));
    }

}
