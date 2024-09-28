package za.ac.cput.util;

public class CustomerHelper {
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    // Add more validation methods as needed

}