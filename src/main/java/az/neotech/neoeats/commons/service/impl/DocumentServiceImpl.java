package az.neotech.neoeats.commons.service.impl;

import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.repository.DocumentRepository;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;
import az.neotech.neoeats.commons.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public DocumentResponse generateDocument(String filename, DocumentType documentType) {
        log.info("Generating document...");
        // todo: different class for each document type with switch case, each will be generated in background async
        log.info("Document generation completed.");
        return null;
    }
}
