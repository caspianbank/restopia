package az.restopia.commons.domain.response;

import az.restopia.commons.domain.enums.DocumentGenerationStatus;
import az.restopia.commons.domain.enums.DocumentType;
import az.restopia.commons.domain.enums.FileType;

public record DocumentResponse(
        String name,
        String tenantCode,
        DocumentType documentType,
        FileType fileType,
        DocumentGenerationStatus documentGenerationStatus
) {
}
