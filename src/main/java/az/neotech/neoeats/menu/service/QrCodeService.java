package az.neotech.neoeats.menu.service;

public interface QrCodeService {
    void generateQrImageForTable(Long tableId, String qrCodeText);

}
