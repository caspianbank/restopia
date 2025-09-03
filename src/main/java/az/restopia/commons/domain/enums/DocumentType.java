package az.restopia.commons.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentType {
    QR_CODES("QR"),
    REPORTS("R"),
    MENU("M");

    private final String code;
}
