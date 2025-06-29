package az.neotech.neoeats.commons.component;

import java.util.Random;

public final class RandomCodeGenerator {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateAlphaNumericRandomCode(int length) {
        StringBuilder result = new StringBuilder();
        var random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return result.toString();
    }

    private RandomCodeGenerator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
