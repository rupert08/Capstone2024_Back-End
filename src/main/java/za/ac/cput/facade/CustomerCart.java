package za.ac.cput.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;
import za.ac.cput.service.CustomerService;

@Component
public class CustomerCart {
    private CartService cartService;
    private CustomerService customerService;

    @Autowired
    public CustomerCart(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    public Cart saveCart(Cart obj){
        if(customerService.read(obj.getCustomer().getUserId()) == null){
            return null;
        }
        return obj;
    }
}
