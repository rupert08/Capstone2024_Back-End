package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.facade.AddCartItem;
import za.ac.cput.facade.CustomerCart;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.service.CartService;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private CustomerCart customerCart;
    private AddCartItem addCartItem;

    @Autowired
    public CartController(CartService cartService, CustomerCart customerCart, AddCartItem addCartItem){
        this.cartService = cartService;
        this.customerCart = customerCart;
        this.addCartItem = addCartItem;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> create(@RequestBody Cart obj){
        Cart cart = CartFactory.createCart(obj.getCustomer());
        if(customerCart.saveCart(cart) == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        Cart cartObj = cartService.create(cart);
        if(cartObj== null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartObj);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Cart> read(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.read(id));
    }


}
