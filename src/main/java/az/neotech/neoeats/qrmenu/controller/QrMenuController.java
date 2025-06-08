package az.neotech.neoeats.qrmenu.controller;

import az.neotech.neoeats.qrmenu.domain.dto.response.QrMenuResponse;
import az.neotech.neoeats.qrmenu.service.QrMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/qrmenu")
@RequiredArgsConstructor
public class QrMenuController {

    private final QrMenuService qrMenuService;
    @GetMapping("/{qrCode}")
    public QrMenuResponse getMenuByQrCode(@PathVariable String qrCode) {
        return qrMenuService.getMenuByQrCode(qrCode);
    }
}
