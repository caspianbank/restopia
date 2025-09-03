package az.restopia.menu.service;

import az.restopia.menu.domain.request.QrCodeRequest;

public interface QrCodeService {
    void generateQrImageForTable(Long tableId, String qrCodeText);

    void generateQrCodes(QrCodeRequest qrCodeRequest);

}
