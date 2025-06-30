package az.neotech.neoeats.commons.service;

import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;

public interface DocumentService {
    DocumentResponse generateDocument(String filename, DocumentType documentType);
}
