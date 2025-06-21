package az.neotech.neoeats.menu.service;

import az.neotech.neoeats.menu.domain.response.QrMenuResponse;

public interface QrMenuService {
    QrMenuResponse getMenuByQrCode(String qrCode);
}
