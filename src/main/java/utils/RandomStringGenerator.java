package utils;

import java.security.SecureRandom;

public class RandomStringGenerator {

    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+<>?{}[]|";
    private static final SecureRandom RANDOM = new SecureRandom();

    // 1. Generate Alpha String
    public static String generateAlpha(int length) {
        return generateRandomString(ALPHA, length);
    }

    // 2. Generate AlphaNumeric String
    public static String generateAlphaNumeric(int length) {
        return generateRandomString(ALPHA + NUMERIC, length);
    }

    // 3. Generate Numeric String
    public static String generateNumeric(int length) {
        return generateRandomString(NUMERIC, length);
    }

    // 4. Generate AlphaNumeric with Special Characters String
    public static String generateAlphaNumericSpecialChars(int length) {
        return generateRandomString(ALPHA + NUMERIC + SPECIAL_CHARS, length);
    }

    // Helper method to generate random string
    private static String generateRandomString(String characters, int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(RANDOM.nextInt(characters.length())));
        }
        return result.toString();
    }


}

