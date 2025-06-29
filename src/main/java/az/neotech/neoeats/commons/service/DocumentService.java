package az.neotech.neoeats.commons.service;

import az.neotech.neoeats.commons.enums.DocumentType;
import az.neotech.neoeats.commons.response.DocumentResponse;

public interface DocumentService {
    DocumentResponse generateDocument(String filename, DocumentType documentType);
}
