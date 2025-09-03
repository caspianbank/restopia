package az.restopia.commons.service.impl;

import az.restopia.commons.component.RandomCodeGenerator;
import az.restopia.commons.domain.entity.Document;
import az.restopia.commons.domain.enums.DocumentGenerationStatus;
import az.restopia.commons.domain.enums.DocumentType;
import az.restopia.commons.domain.enums.FileType;
import az.restopia.commons.domain.request.DocumentRequest;
import az.restopia.commons.repository.DocumentRepository;
import az.restopia.commons.domain.response.DocumentResponse;
import az.restopia.commons.service.DocumentService;
import az.restopia.commons.service.generator.MenuDocumentGenerator;
import az.restopia.commons.service.generator.QrCodeDocumentGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final QrCodeDocumentGenerator qrCodeDocumentGenerator;
    private final MenuDocumentGenerator menuDocumentGenerator;

    @Override
    public DocumentResponse generateDocument(DocumentRequest documentRequest,
                                             Consumer<String> onDocumentReadyForDownload) {
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
            case QR_CODES -> qrCodeDocumentGenerator.generateDocument(documentRequest, onDocumentReadyForDownload);
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
