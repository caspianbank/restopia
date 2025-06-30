package az.neotech.neoeats.commons.service.impl;

import az.neotech.neoeats.commons.component.RandomCodeGenerator;
import az.neotech.neoeats.commons.domain.entity.Document;
import az.neotech.neoeats.commons.domain.enums.DocumentGenerationStatus;
import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.enums.FileType;
import az.neotech.neoeats.commons.domain.request.DocumentRequest;
import az.neotech.neoeats.commons.repository.DocumentRepository;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;
import az.neotech.neoeats.commons.service.DocumentService;
import az.neotech.neoeats.commons.service.generator.MenuDocumentGenerator;
import az.neotech.neoeats.commons.service.generator.QrCodeDocumentGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final QrCodeDocumentGenerator qrCodeDocumentGenerator;
    private final MenuDocumentGenerator menuDocumentGenerator;

    @Override
    public DocumentResponse generateDocument(DocumentRequest documentRequest) {
        final DocumentType documentType = documentRequest.documentType();
        var document = Document.builder()
                .name(documentRequest.name())
                .documentCode(RandomCodeGenerator.generateAlpNumRandomCode(documentType.getCode(), 10))
                .tenantCode(documentRequest.tenantCode())
                .documentType(documentType)
                .fileType(getFileTypeByDocumentType(documentType))
                .documentGenerationStatus(DocumentGenerationStatus.IN_PROGRESS)
                .build();

        Document savedDocument = documentRepository.save(document);
        log.info("Document saved: {}", document);

        switch (documentType) {
            case QR_CODES -> qrCodeDocumentGenerator.generateDocument(documentRequest);
            case MENU -> menuDocumentGenerator.generateDocument(documentRequest);
            default -> throw new IllegalArgumentException("Unsupported document type: " + documentType);
        }

        return new DocumentResponse(
                savedDocument.getName(),
                savedDocument.getTenantCode(),
                savedDocument.getDocumentType(),
                savedDocument.getFileType(),
                savedDocument.getDocumentGenerationStatus()
        );
    }

    private FileType getFileTypeByDocumentType(DocumentType documentType) {
        return switch (documentType) {
            case QR_CODES, MENU -> FileType.PDF;
            default -> throw new IllegalArgumentException("Unsupported document type: " + documentType);
        };
    }
}
