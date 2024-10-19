package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerUserPrincipal;
import za.ac.cput.domain.UserPrincipalSec;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.repository.CustomerRepository;

@Service
public class UserDetailsServiceSecurity implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        Customer customer = customerRepository.findByUsername(username);

        if(admin != null) {
            return new UserPrincipalSec(admin);
        } else if (customer != null) {
            return new CustomerUserPrincipal(customer);
        }
        System.out.println("User not found...");
        throw new UsernameNotFoundException("User not found...");
    }
}
