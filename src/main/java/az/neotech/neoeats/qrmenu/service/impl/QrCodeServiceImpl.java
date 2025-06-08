package az.neotech.neoeats.qrmenu.service.impl;

import az.neotech.neoeats.qrmenu.service.QrCodeService;
import az.neotech.neoeats.qrmenu.util.QrCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {

    private final QrCodeGenerator qrCodeGenerator;

    @Override
    public void generateQrImageForTable(Long tableId, String qrCodeText) {
        try {
            // Faylın yerləşəcəyi qovluğu yoxla
            File directory = new File("qr_codes");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Faylın yolu
            String filePath = "qr_codes/qr_table_" + tableId + ".png";

            // QR kodu fayla yaz
            qrCodeGenerator.generateQrCodeImageToFile(qrCodeText, 300, 300, filePath);
        } catch (IOException | WriterException e) {
            throw new RuntimeException("QR kod şəkli yaradılarkən xəta baş verdi", e);
        }
    }
}
