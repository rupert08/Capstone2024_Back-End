// AddressFactory.java
package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.AddressHelper;

public class AddressFactory {

    public static Address createAddress(String streetNumber, String streetName, String city, String state, String postalCode, Customer customerId) {
        if (!AddressHelper.isValidStreetNumber(streetNumber)) {
            throw new IllegalArgumentException("Invalid street number");
        }
        if (!AddressHelper.isValidPostalCode(postalCode)) {
            throw new IllegalArgumentException("Invalid postal code");
        }
        if (AddressHelper.isValidStandaloneAddress(streetNumber, streetName, city, state, postalCode)) {
            return Address.builder()
                    .streetNumber(streetNumber)
                    .streetName(streetName)
                    .city(city)
                    .state(state)
                    .postalCode(postalCode)
                    .customer(customerId)
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid address");
        }

    }
    public static Address createAddress(String streetNumber, String streetName, String city, String state, String postalCode) {
        return Address.builder()
                .streetNumber(streetNumber)
                .streetName(streetName)
                .city(city)
                .state(state)
                .postalCode(postalCode)
                .build();
    }}