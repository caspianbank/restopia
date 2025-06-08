package az.neotech.neoeats.qrmenu.service;

public interface QrCodeService {
    void generateQrImageForTable(Long tableId, String qrCodeText);

}
