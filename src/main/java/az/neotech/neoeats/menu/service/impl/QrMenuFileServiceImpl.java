package az.neotech.neoeats.menu.service.impl;

import az.neotech.neoeats.commons.component.RandomCodeGenerator;
import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.request.DocumentRequest;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;
import az.neotech.neoeats.commons.service.DocumentService;
import az.neotech.neoeats.layout.service.TableService;
import az.neotech.neoeats.menu.domain.event.DocumentReadyNotificationEvent;
import az.neotech.neoeats.menu.domain.request.GenerateFileRequest;
import az.neotech.neoeats.menu.domain.response.GenerateFileResponse;
import az.neotech.neoeats.menu.service.QrMenuFileService;
import az.neotech.neoeats.menu.service.WebSocketMessageService;
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
    private final TableService tableService;
    private final WebSocketMessageService webSocketMessageService;

    @Value("${qr-menu.base.url:https://neoeats.az/qr-menu}")
    private String baseUrl;

    @Override
    public GenerateFileResponse generateFile(GenerateFileRequest request, String generatedByUsername) {
        final String tenantCode = request.tenantCode();
        final String randomCode = RandomCodeGenerator.generateAlpNumRandomCode(6);
        final String filename = tenantCode + "_QR_MENU_DOC_" + randomCode;

        List<String> qrCodeUrlList = tableService.getAll().stream()
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
