package az.neotech.neoeats.menu.controller;

import az.neotech.neoeats.menu.domain.request.GenerateFileRequest;
import az.neotech.neoeats.menu.domain.response.GenerateFileResponse;
import az.neotech.neoeats.menu.domain.response.QrMenuResponse;
import az.neotech.neoeats.menu.service.QrMenuFileService;
import az.neotech.neoeats.menu.service.QrMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qr-menu-file")
public class QrMenuFileController {

    private final QrMenuService qrMenuService;
    private final QrMenuFileService qrMenuFileService;

    @GetMapping("/{qrCode}")
    public QrMenuResponse getMenuByQrCode(@PathVariable String qrCode) {
        return qrMenuService.getMenuByQrCode(qrCode);
    }

    @PostMapping("/generate")
    public GenerateFileResponse generateFileContainsQrCodes(@Valid @RequestBody GenerateFileRequest request) {
        return qrMenuFileService.generateFile(request);
    }
}
