package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.CartItemRepository;
import za.ac.cput.service.interfaces.ICartItemService;
import za.ac.cput.service.interfaces.ICartService;
import za.ac.cput.service.interfaces.IProductService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final IProductService productService;
    private final ICartService cartService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, IProductService productService, ICartService cartService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.cartService = cartService;
    }

    public CartItem create(CartItem cartItem) {
        // Retrieve and attach the Product to avoid detached entity issue
        Product product = productService.read(cartItem.getProduct().getProductId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        // Ensure the product entity is managed
        cartItem.setProduct(product);

        // Retrieve and attach the Cart to avoid detached entity issue
        Cart cart = cartService.read(cartItem.getCart().getCartId());
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        cartItem.setCart(cart);

        // Now save the cart item
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        System.out.println("Saved CartItem: " + savedCartItem);  // Log saved item
        return savedCartItem;
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
