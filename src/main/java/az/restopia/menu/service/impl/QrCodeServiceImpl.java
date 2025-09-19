package az.restopia.menu.service.impl;

import az.restopia.menu.domain.request.QrCodeRequest;
import az.restopia.menu.service.QrCodeService;
import az.restopia.menu.util.QrCodeGenerator;
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

    @Override
    public void generateQrCodes(QrCodeRequest qrCodeRequest) {
        /*
          todo: find all tables of tenant and generate QR codes for each table
            each qr code will be in a table with separate image and each image name will show table code and name
            e.g. qr_masa_1_3XZ4DF.png, qr_masa_2_89CV4R.png

            at the end of the process, zip file can be downloaded, so they can send this file to the banner printing
            company
         */
    }
}
