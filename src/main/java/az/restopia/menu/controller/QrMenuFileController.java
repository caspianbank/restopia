package az.restopia.menu.controller;

import az.restopia.menu.domain.request.GenerateFileRequest;
import az.restopia.menu.domain.response.GenerateFileResponse;
import az.restopia.menu.service.QrMenuFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qr-menu-file")
public class QrMenuFileController {

    private final QrMenuFileService qrMenuFileService;

    @PostMapping("/generate")
    public GenerateFileResponse generateFileContainsQrCodes(
            @Valid @RequestBody GenerateFileRequest request,
            @RequestHeader(name = "X-NEO-USER", required = false) String username
    ) {
        return qrMenuFileService.generateFile(request, username);
    }
}
