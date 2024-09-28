package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
//import za.ac.cput.util.AddressHelper;
//import za.ac.cput.util.ContactHelper;
import za.ac.cput.util.Helper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerFactory {
    public static Customer createCustomer(String username, String password) {
//        if (ContactHelper.isValidEmail(username) || Helper.isNullOrEmpty(password)) {
//
//            throw new IllegalArgumentException(
//                    "Can not have null or empty values in required fields!!!");
//        }

        Contact contact = Contact.builder()
                .email(username)
                .build();

        return Customer.builder()
                .contact(contact)
                .username(username)
                .password(password)
                //
                .build();
    }


    public static Customer createCustomer(String username, String firstname, String lastname, Contact contact, String password, List<Address> address) {
        if (Helper.isNullOrEmpty(username)) {
            username = contact.getEmail();
        }
//        if (ContactHelper.isValidEmail(username)) {
//            throw new IllegalArgumentException("Invalid username ");
//        }
//        if (Helper.isNullOrEmpty(firstname)) {
//            throw new IllegalArgumentException("First name cannot be null or empty");
//        }
//        if (Helper.isNullOrEmpty(lastname)) {
//            throw new IllegalArgumentException("Last name cannot be null or empty");
//        }
//        if (contact == null) {
//            throw new IllegalArgumentException("Contact cannot be null");
//        }
//        if (Helper.isNullOrEmpty(password)) {
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
//        if (address == null) {
//            address = new HashSet<>();
//        }
        return  Customer.builder()
                .username(username)
                .firstName(firstname)
                .lastName(lastname)
                .contact(contact)
                .password(password)
                .address(address)
                .build();
    }
    public static Customer createCustomer(String firstName, String lastName, Contact contact) {
            Customer.builder().username(contact.getEmail()).build();

//        if (ContactHelper.isValidEmail(username)) {
//            throw new IllegalArgumentException("Invalid username ");
//        }
//        if (Helper.isNullOrEmpty(firstName)) {
//            throw new IllegalArgumentException("First name cannot be null or empty");
//        }
//        if (Helper.isNullOrEmpty(lastName)) {
//            throw new IllegalArgumentException("Last name cannot be null or empty");
//        }
//        if (contact == null) {
//            throw new IllegalArgumentException("Contact cannot be null");
//        }
//        if (Helper.isNullOrEmpty(password)) {
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
        return  Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .contact(contact)
                .build();
    }
    public static Customer createCustomer(String username, String password, String firstName, String lastName, Contact contact){
        if (Helper.isNullOrEmpty(username)) {
            username = contact.getEmail();
        }
        return Customer.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .contact(contact)
                .build();
    }
}

