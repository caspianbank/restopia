package az.neotech.neoeats.menu.service.impl;

import az.neotech.neoeats.commons.component.RandomCodeGenerator;
import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.request.DocumentRequest;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;
import az.neotech.neoeats.commons.service.DocumentService;
import az.neotech.neoeats.layout.service.TableService;
import az.neotech.neoeats.menu.domain.request.GenerateFileRequest;
import az.neotech.neoeats.menu.domain.response.GenerateFileResponse;
import az.neotech.neoeats.menu.service.QrMenuFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrMenuFileServiceImpl implements QrMenuFileService {

    private final DocumentService documentService;
    private final TableService tableService;

    @Value("${qr-menu.base.url:https://neoeats.az/qr-menu}")
    private String baseUrl;

    @Override
    public GenerateFileResponse generateFile(GenerateFileRequest request) {
        final String tenantCode = request.tenantCode();
        final String randomCode = RandomCodeGenerator.generateAlpNumRandomCode(6);
        final String filename = tenantCode + "_QR_MENU_DOC_" + randomCode;

        List<String> qrCodeUrlList = tableService.getAll().stream()
                .map(table -> baseUrl + "?tenantCode=" + tenantCode + "&tableCode=" + table.getCode())
                .toList();

        var documentRequest = new DocumentRequest(filename, tenantCode, DocumentType.QR_CODES,
                new DocumentRequest.QrCodeDocumentContent(qrCodeUrlList));
        DocumentResponse documentResponse = documentService.generateDocument(documentRequest);
        return new GenerateFileResponse(documentResponse.name(), documentResponse.fileType());
    }
}
