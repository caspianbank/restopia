package az.neotech.neoeats.commons.domain.response;

import az.neotech.neoeats.commons.domain.enums.DocumentGenerationStatus;
import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.enums.FileType;

public record DocumentResponse(
        String name,
        String tenantCode,
        DocumentType documentType,
        FileType fileType,
        DocumentGenerationStatus documentGenerationStatus
) {
}
