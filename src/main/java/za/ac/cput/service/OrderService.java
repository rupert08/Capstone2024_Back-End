package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Order;
import za.ac.cput.repository.OderRepository;
import za.ac.cput.service.interfaces.IOrderService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    private final OderRepository orderRepository;

    @Autowired
    public OrderService(OderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order read(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Set<Order> getAll() {
        return orderRepository.findAll().stream().collect(Collectors.toSet());
    }
}




