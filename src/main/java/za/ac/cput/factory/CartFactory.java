package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.CartHelper;
import za.ac.cput.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class CartFactory {

    public static Cart createCart(Customer customer, List<CartItem> cartItems) {
        Helper.checkNullParam(customer);

        Cart cart = Cart.builder()
                .customer(customer)
                .cartItems(cartItems != null ? cartItems : new ArrayList<>())
                .build();

        cart.calculateTotalPrice();
        return cart;
    }

    public static Cart createCart(Customer customer) {

        return createCart(customer, new ArrayList<>());
    }

    public static void addCartItem(Cart cart, CartItem cartItem) {
        Helper.checkNullParam(cart);
        Helper.checkNullParam(cartItem);
        CartHelper.checkValidItemsQuantity(cart.getCartItems().size() + 1);
        cart.addItem(cartItem);
    }

    public static void removeCartItem(Cart cart, CartItem cartItem) {
        Helper.checkNullParam(cart);
        Helper.checkNullParam(cartItem);
        CartHelper.checkValidItemsQuantity(cart.getCartItems().size() - 1);
        cart.removeItem(cartItem);
    }
}
