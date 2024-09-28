package za.ac.cput.util;

public class CartItemHelper {
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public static boolean isValidItemTotalPrice(float itemTotalPrice) {
        return itemTotalPrice >= 0;
    }

    public static void checkValidQuantity(int quantity) {
        if (!isValidQuantity(quantity)) {
            throw new RuntimeException("Invalid quantity");
        }
    }

    public static void checkValidItemTotalPrice(float itemTotalPrice) {
        if (!isValidItemTotalPrice(itemTotalPrice)) {
            throw new RuntimeException("Invalid item total price");
        }
    }
}
