package az.restopia.commons.domain.request;

import az.restopia.commons.domain.enums.DocumentType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record DocumentRequest(
        String name,
        String tenantCode,
        DocumentType documentType,
        DocumentContent contents
) {

    public static sealed class DocumentContent permits QrCodeDocumentContent {
    }

    @Setter
    @Getter
    public static final class QrCodeDocumentContent extends DocumentContent {
        private List<String> qrCodes;

        public QrCodeDocumentContent(List<String> qrCodes) {
            super();
            this.qrCodes = qrCodes;
        }
    }
}
