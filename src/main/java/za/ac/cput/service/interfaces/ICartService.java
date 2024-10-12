package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Cart;

import java.util.Set;

public interface ICartService {
    Cart create(Cart cart);
    Cart read(Long id);
    Cart update(Cart cart);
    void delete(Long id);
    Set<Cart> getAll();
}