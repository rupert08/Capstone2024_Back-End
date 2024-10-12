package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.service.interfaces.ICartService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart read(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Set<Cart> getAll() {
        return cartRepository.findAll().stream().collect(Collectors.toSet());
    }

    public void addCartItemToCart(Cart cart, CartItem cartItem) {
        cart.addItem(cartItem);
        cartRepository.save(cart);
    }

    public void removeCartItemFromCart(Cart cart, CartItem cartItem) {
        cart.removeItem(cartItem);
        cartRepository.save(cart);
    }
}