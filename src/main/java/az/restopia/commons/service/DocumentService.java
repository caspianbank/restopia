package az.restopia.commons.service;

import az.restopia.commons.domain.request.DocumentRequest;
import az.restopia.commons.domain.response.DocumentResponse;
import java.util.function.Consumer;

public interface DocumentService {
    DocumentResponse generateDocument(DocumentRequest documentRequest, Consumer<String> onDocumentReadyForDownload);
}
