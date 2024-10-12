package za.ac.cput.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CartItem;
import za.ac.cput.facade.AddCartItem;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.service.CartItemService;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    private CartItemService cartItemService;
    private AddCartItem addCartItem;

    public CartItemController(CartItemService cartItemService, AddCartItem addCartItem){
        this.cartItemService = cartItemService;
        this.addCartItem = addCartItem;
    }

    @PostMapping("/save")
    public ResponseEntity<CartItem> save(@RequestBody CartItem obj){
        CartItem builtObj = CartItemFactory.createCartItem(obj.getProduct(),obj.getCart(), obj.getQuantity());
        if(builtObj == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        CartItem m1 = addCartItem.saveItem(builtObj);

        if(m1 == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(m1);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CartItem> read(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cartItemService.read(id));
    }

    @PutMapping("/update")
    public ResponseEntity<CartItem> update(@RequestBody CartItem obj){
        CartItem builtObj = CartItemFactory.createCartItem(obj.getProduct(), obj.getCart(), obj.getQuantity());

        if(builtObj == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }

        CartItem cartItem = addCartItem.updateCart(builtObj);
        if(cartItem == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartItem);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        cartItemService.delete(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<CartItem>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cartItemService.getAll());
    }

}
