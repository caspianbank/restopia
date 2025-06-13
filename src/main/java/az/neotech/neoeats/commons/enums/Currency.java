package az.neotech.neoeats.commons.enums;

import lombok.Getter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
public enum Currency {
    AZN("AZN", "Azerbaijani Manat", "₼", 2),
    USD("USD", "US Dollar", "$", 2),
    EUR("EUR", "Euro", "€", 2),
    TRY("TRY", "Turkish Lira", "₺", 2);

    private final String code;
    private final String displayName;
    private final String symbol;
    private final int decimalPlaces;

    Currency(String code, String displayName, String symbol, int decimalPlaces) {
        this.code = code;
        this.displayName = displayName;
        this.symbol = symbol;
        this.decimalPlaces = decimalPlaces;
    }

    public static Currency fromCode(String code) {
        for (Currency currency : values()) {
            if (currency.code.equalsIgnoreCase(code)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unknown currency code: " + code);
    }

    public boolean hasMinorUnits() {
        return decimalPlaces > 0;
    }

    public String formatAmount(BigDecimal amount) {
        if (amount == null) {
            return symbol + "0";
        }

        DecimalFormat formatter = new DecimalFormat();
        formatter.setMinimumFractionDigits(decimalPlaces);
        formatter.setMaximumFractionDigits(decimalPlaces);
        formatter.setGroupingUsed(true);

        return symbol + formatter.format(amount);
    }

    @Override
    public String toString() {
        return code + " (" + displayName + ")";
    }

}
