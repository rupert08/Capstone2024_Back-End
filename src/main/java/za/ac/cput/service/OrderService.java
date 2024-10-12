// OrderService.java
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Shipping;
import za.ac.cput.repository.OrderRepository;
import za.ac.cput.repository.ShippingRepository;
import za.ac.cput.service.interfaces.IOrderService;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ShippingRepository shippingRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ShippingRepository shippingRepository) {
        this.orderRepository = orderRepository;
        this.shippingRepository = shippingRepository;
    }

    @Override
    public Order create(Order order) {
        // Save the Shipping instance first
        Shipping shipping = order.getShipping();
        if (shipping != null) {
            shippingRepository.save(shipping);
        }
        // Now save the Order instance
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
    public List<Order> getAll() {
        return  orderRepository.findAll();
    }
}