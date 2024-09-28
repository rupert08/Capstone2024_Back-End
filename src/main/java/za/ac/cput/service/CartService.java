package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.service.interfaces.ICartService;

@Service
public class CartService implements ICartService {

    private CartRepository cartRepository;

    @Autowired
    CartService(CartRepository cartRepository){this.cartRepository = cartRepository;}

    @Override
    public Cart create(Cart obj) {
        return cartRepository.save(obj);
    }

    @Override
    public Cart read(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart obj) {
        return cartRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
