package za.ac.cput.util;

public class CartHelper {
    public static boolean isValidItemsQuantity(int quantity) {
        return quantity >= 0;
    }

    public static boolean isValidTotalPrice(float totalPrice) {
        return totalPrice >= 0;
    }

    public static void checkValidItemsQuantity(int quantity) {
        if (!isValidItemsQuantity(quantity)) {
            throw new RuntimeException("Invalid items quantity");
        }
    }

    public static void checkValidTotalPrice(float totalPrice) {
        if (!isValidTotalPrice(totalPrice)) {
            throw new RuntimeException("Invalid total price");
        }
    }
}
