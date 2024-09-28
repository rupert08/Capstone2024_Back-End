package za.ac.cput.util;

public class AddressHelper {

    public static boolean isValidPostalCode(String postalCode) {
        return postalCode.matches("\\d{4,5}");
    }

    public static boolean isValidStreetNumber(String streetNumber) {

        return streetNumber.matches("\\d+[a-zA-Z]?");
    }

    public static boolean isValidStandaloneAddress(String streetNumber, String streetName, String city, String state, String postalCode) {
        return !Helper.isNullOrEmpty(streetNumber) && !Helper.isNullOrEmpty(streetName) && !Helper.isNullOrEmpty(city) && !Helper.isNullOrEmpty(state) && isValidPostalCode(postalCode);

    }

    public static boolean isValidApartmentAddress(String apartmentNumber, String complexName, String streetName, String city, String state, String postalCode) {
        return !Helper.isNullOrEmpty(apartmentNumber) && !Helper.isNullOrEmpty(complexName) && !Helper.isNullOrEmpty(streetName) && !Helper.isNullOrEmpty(city) && !Helper.isNullOrEmpty(state) && isValidPostalCode(postalCode);
    }

    public static boolean isValidUnitAddress(String unitNumber, String buildingName, String streetName, String city, String state, String postalCode) {
        return !Helper.isNullOrEmpty(unitNumber) && !Helper.isNullOrEmpty(buildingName) && !Helper.isNullOrEmpty(streetName) && !Helper.isNullOrEmpty(city) && !Helper.isNullOrEmpty(state) && isValidPostalCode(postalCode)||Helper.isNullOrEmpty(postalCode);
    }
    public static boolean isValidState(String state) {
        return state != null && !state.trim().isEmpty();
    }
}