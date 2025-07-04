package az.neotech.neoeats.menu.service;

import az.neotech.neoeats.menu.domain.request.GenerateFileRequest;
import az.neotech.neoeats.menu.domain.response.GenerateFileResponse;

public interface QrMenuFileService {

    GenerateFileResponse generateFile(GenerateFileRequest request, String generatedByUsername);
}
