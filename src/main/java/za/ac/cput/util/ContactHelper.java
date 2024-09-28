package za.ac.cput.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ContactHelper {

    public static boolean isValidEmail(String email) {
        boolean result = false;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = true;
        }
        return result;
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+?\\d{10,15}");
    }
   }