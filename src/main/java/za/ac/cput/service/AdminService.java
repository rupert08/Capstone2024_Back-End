package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import za.ac.cput.config.JWTService;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.service.interfaces.IAdminService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {

    private AdminRepository adminRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin create(Admin object) {
        return adminRepository.save(object);
    }

    @Override
    public Admin read(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {

        return adminRepository.save(admin);
    }

    @Override
    public void delete(Long id) {adminRepository.deleteById(id);
    }

    @Override
    public Set<Admin> getAll() {
        return adminRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    public String verify(Admin admin) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(admin.getUsername());
        }
        return "Failed to authenticate admin";
    }
}