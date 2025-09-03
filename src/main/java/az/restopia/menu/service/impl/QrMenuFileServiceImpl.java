package az.restopia.menu.service.impl;

import az.restopia.commons.component.RandomCodeGenerator;
import az.restopia.commons.domain.enums.DocumentType;
import az.restopia.commons.domain.request.DocumentRequest;
import az.restopia.commons.domain.response.DocumentResponse;
import az.restopia.commons.service.DocumentService;
import az.restopia.layout.service.DiningTableService;
import az.restopia.menu.domain.event.DocumentReadyNotificationEvent;
import az.restopia.menu.domain.request.GenerateFileRequest;
import az.restopia.menu.domain.response.GenerateFileResponse;
import az.restopia.menu.service.QrMenuFileService;
import az.restopia.menu.service.WebSocketMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrMenuFileServiceImpl implements QrMenuFileService {

    private final DocumentService documentService;
    private final DiningTableService diningTableService;
    private final WebSocketMessageService webSocketMessageService;

    @Value("${qr-menu.base.url:https://neoeats.az/qr-menu}")
    private String baseUrl;

    @Override
    public GenerateFileResponse generateFile(GenerateFileRequest request, String generatedByUsername) {
        final String tenantCode = request.tenantCode();
        final String randomCode = RandomCodeGenerator.generateAlpNumRandomCode(6);
        final String filename = tenantCode + "_QR_MENU_DOC_" + randomCode;

        List<String> qrCodeUrlList = diningTableService.getAll().stream()
                .map(table -> baseUrl + "?tenantCode=" + tenantCode + "&tableCode=" + table.getCode())
                .toList();

        var qrCodeDocumentContent = new DocumentRequest.QrCodeDocumentContent(qrCodeUrlList);
        var documentRequest = new DocumentRequest(filename, tenantCode, DocumentType.QR_CODES, qrCodeDocumentContent);

        Consumer<String> onDocumentReadyCallback = onDocumentReadyCallback(documentRequest, generatedByUsername);
        DocumentResponse documentResponse = documentService.generateDocument(documentRequest, onDocumentReadyCallback);
        return new GenerateFileResponse(documentResponse.name(), documentResponse.fileType());
    }

    private Consumer<String> onDocumentReadyCallback(DocumentRequest documentRequest, String username) {
        return (documentUrl) -> {
            log.info("Document {} generation completed for user {}", documentRequest.name(), username);
            var event = new DocumentReadyNotificationEvent(documentRequest.name(), documentUrl);
            webSocketMessageService.sendDocumentReadyNotification(username, event);
        };
    }
}
