package az.neotech.neoeats.menu.service.impl;

import az.neotech.neoeats.commons.component.RandomCodeGenerator;
import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.response.DocumentResponse;
import az.neotech.neoeats.commons.service.DocumentService;
import az.neotech.neoeats.menu.domain.request.GenerateFileRequest;
import az.neotech.neoeats.menu.domain.response.GenerateFileResponse;
import az.neotech.neoeats.menu.service.QrMenuFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrMenuFileServiceImpl implements QrMenuFileService {

    private final DocumentService documentService;

    @Override
    public GenerateFileResponse generateFile(GenerateFileRequest request) {
        String randomCode = RandomCodeGenerator.generateAlphaNumericRandomCode(6);
        final String filename = request.tenantCode() + "_QR_MENU_DOC_" + randomCode;
        DocumentResponse documentResponse = documentService.generateDocument(filename, DocumentType.QR_CODES);
        return new GenerateFileResponse(documentResponse.name(), documentResponse.fileType());
    }
}
