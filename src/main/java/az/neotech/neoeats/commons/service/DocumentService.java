package az.neotech.neoeats.commons.service;

import az.neotech.neoeats.commons.domain.request.DocumentRequest;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;

public interface DocumentService {
    DocumentResponse generateDocument(DocumentRequest documentRequest);
}
