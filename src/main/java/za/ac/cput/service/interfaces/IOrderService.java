package za.ac.cput.service.interfaces;
import za.ac.cput.domain.Order;
import za.ac.cput.service.interfaces.IService;

import java.util.List;
import java.util.Set;

public interface IOrderService extends IService<Order, Long> {
    List<Order> getAll();
}
