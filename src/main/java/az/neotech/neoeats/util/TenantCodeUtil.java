package az.neotech.neoeats.util;

import org.springframework.util.StringUtils;

import java.security.SecureRandom;

public final class TenantCodeUtil {

    private static final int MIN_TEXT_LEN = 3;
    private static final int MAX_TEXT_LEN = 8;
    private static final String PREFIX = "TEN";
    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private TenantCodeUtil() {
        throw new IllegalStateException("Cannot create instance of utility class");
    }

    public static String generateCode(String text) {
        if (!StringUtils.hasText(text) || text.length() < MIN_TEXT_LEN) {
            throw new IllegalArgumentException("Text must be at least 3 characters long.");
        }

        String trimmedText = text.length() > MAX_TEXT_LEN ? text.substring(0, MAX_TEXT_LEN) : text;

        StringBuilder codeBuilder = new StringBuilder(PREFIX);

        for (int i = 0; i < trimmedText.length(); i++) {
            codeBuilder.append(trimmedText.charAt(i));
            if (i != trimmedText.length() - 1) {
                codeBuilder.append(randomSymbol());
            }
        }

        return codeBuilder.toString().toUpperCase();
    }

    private static char randomSymbol() {
        return SYMBOLS.charAt(SECURE_RANDOM.nextInt(SYMBOLS.length()));
    }
}
