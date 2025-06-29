package az.neotech.neoeats.commons.response;

import az.neotech.neoeats.commons.enums.DocumentGenerationStatus;
import az.neotech.neoeats.commons.enums.DocumentType;
import az.neotech.neoeats.commons.enums.FileType;

public record DocumentResponse(
        String name,
        String tenantCode,
        DocumentType documentType,
        FileType fileType,
        DocumentGenerationStatus documentGenerationStatus
) {
}
