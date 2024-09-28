package za.ac.cput.util;

public class Helper {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty() || s.isBlank();

    }

    public static void checkStringParam(String s) {
        if (isNullOrEmpty(s)) {
            throw new RuntimeException("String parameter is invalid");
        }
    }

    public static boolean checkNullParam(Object obj) {
//        if (obj == null) {
//            throw new RuntimeException("Object parameter is null");
//        }
        return obj == null;
    }

    public static void checkZeroOrNegativeParam(int number) {
        if (number <= 0) {
            throw new RuntimeException("Number parameter is invalid");
        }
    }

    public static void checkZeroOrNegativeParam(float number) {
        if (number <= 0) {
            throw new RuntimeException("Number parameter is invalid");
        }
    }
}
