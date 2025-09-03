package az.restopia.menu.service;

import az.restopia.menu.domain.request.GenerateFileRequest;
import az.restopia.menu.domain.response.GenerateFileResponse;

public interface QrMenuFileService {

    GenerateFileResponse generateFile(GenerateFileRequest request, String generatedByUsername);
}
