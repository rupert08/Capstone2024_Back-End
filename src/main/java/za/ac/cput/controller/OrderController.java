package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Order;
import za.ac.cput.service.OrderService;
import java.util.Set;
@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public String home() {
        return "index.html";
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.create(order);
    }

    @GetMapping("/read/{orderID}")
    public Order readOrder(@PathVariable Long orderID) {
        return orderService.read(orderID);
    }

    @PostMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.update(order);
    }

    @DeleteMapping("/delete/{orderID}")
    public void deleteOrder(@PathVariable Long orderID) {
        orderService.delete(orderID);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public Set<Order> getAllOrders() {
        return orderService.getAll();
    }

    }



