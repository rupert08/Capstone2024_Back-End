package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.service.OrderService;

import java.util.List;
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

    @PutMapping("/update/{orderID}")
   // public Order updateOrder(@RequestBody Order order) {
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderID, @RequestBody Order order) {
        order.setOrderID(orderID);
        Order updatedOrder= orderService.update(order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{orderID}")
    public void deleteOrder(@PathVariable Long orderID) {
        orderService.delete(orderID);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    }



