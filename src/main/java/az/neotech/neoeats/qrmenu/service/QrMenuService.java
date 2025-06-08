package az.neotech.neoeats.qrmenu.service;

import az.neotech.neoeats.qrmenu.domain.dto.response.QrMenuResponse;

public interface QrMenuService {
    QrMenuResponse getMenuByQrCode(String qrCode);
}
