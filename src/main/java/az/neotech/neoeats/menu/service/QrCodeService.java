package az.neotech.neoeats.menu.service;

import az.neotech.neoeats.menu.domain.request.QrCodeRequest;

public interface QrCodeService {
    void generateQrImageForTable(Long tableId, String qrCodeText);

    void generateQrCodes(QrCodeRequest qrCodeRequest);

}
