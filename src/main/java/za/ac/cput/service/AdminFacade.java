
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
//import za.ac.cput.domain.User;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.repository.OrderRepository;
import java.util.List;

@Service
public class AdminFacade {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
