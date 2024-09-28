package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;
import za.ac.cput.util.CartItemHelper;
import za.ac.cput.util.Helper;

public class CartItemFactory {

    public static CartItem createCartItem(Product product, Cart cart, int quantity) {
        Helper.checkNullParam(product);
        Helper.checkNullParam(cart);
        CartItemHelper.checkValidQuantity(quantity);

        CartItem cartItem = CartItem.builder()
                .product(product)
                .cart(cart)
                .quantity(quantity)
                .build();

        cartItem.calculateItemTotalPrice();
        return cartItem;
    }
}
