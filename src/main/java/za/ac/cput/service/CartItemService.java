package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartItemRepository;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.service.interfaces.ICartItemService;
import za.ac.cput.service.interfaces.ICartService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartItemService implements ICartItemService {

    CartItemRepository cartItemRepository;

    @Autowired
    CartItemService(CartItemRepository cartItemRepository){this.cartItemRepository = cartItemRepository;}

    @Override
    public CartItem create(CartItem obj) {
        return cartItemRepository.save(obj);
    }

    @Override
    public CartItem read(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem update(CartItem obj) {
        return cartItemRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public Set<CartItem> getAll() {
        return cartItemRepository.findAll().stream().collect(Collectors.toSet());
    }
}
