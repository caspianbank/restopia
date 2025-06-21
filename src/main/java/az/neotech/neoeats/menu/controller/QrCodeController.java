package az.neotech.neoeats.menu.controller;

import az.neotech.neoeats.menu.domain.request.QrCodeRequest;
import az.neotech.neoeats.menu.service.QrCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qrcode")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @PostMapping("/generate-all")
    public void generateAll(@Valid QrCodeRequest qrCodeRequest) {
        qrCodeService.generateQrCodes(qrCodeRequest);
    }
}
