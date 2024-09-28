package za.ac.cput.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.service.CartItemService;
import za.ac.cput.service.CartService;

@Component
public class AddCartItem {
    private CartItemService cartItemService;
    private CartService cartService;

    @Autowired
    public AddCartItem(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }

    public CartItem saveItem(CartItem obj) {
        Cart cart = cartService.read(obj.getCart().getCartId());
        if(cart == null) {
            return null;
        }
        CartItem cartItem = CartItem.builder().cartItemId(cart.getCartId()).build();

        return cartItemService.update(cartItem);
    }

    public CartItem updateCart(CartItem obj){
        System.out.println(obj);
        Cart cart = cartService.read(obj.getCart().getCartId());
        System.out.println(cart);
        if(cart == null){
            return null;
        }
        CartItem cartItem = CartItem.builder().cartItemId(cart.getCartId()).build();
        return cartItemService.update(cartItem);
    }
}
