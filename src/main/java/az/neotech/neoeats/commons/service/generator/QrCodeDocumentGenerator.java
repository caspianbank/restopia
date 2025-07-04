package az.neotech.neoeats.commons.service.generator;

import az.neotech.neoeats.commons.component.RandomCodeGenerator;
import az.neotech.neoeats.commons.domain.request.DocumentRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
@Component
public class QrCodeDocumentGenerator {

    @Value("${app.qr-code.width:200}")
    private int qrCodeWidth;

    @Value("${app.qr-code.height:200}")
    private int qrCodeHeight;

    @Value("${app.document.output-path:/tmp}")
    private String outputPath;

    @Async
    public void generateDocument(DocumentRequest documentRequest, Consumer<String> onDocumentReadyForDownload) {
        String filename = documentRequest.name();
        log.info("Generating QR code document with filename: {}", filename);

        try {
            var pdfPath = Path.of(outputPath, filename + ".pdf");
            var writer = new PdfWriter(pdfPath.toString());
            var pdf = new PdfDocument(writer);
            var pdfDocument = new Document(pdf);

            var qrCodeWriter = new QRCodeWriter();

            if (!(documentRequest.contents() instanceof DocumentRequest.QrCodeDocumentContent qrCodeDocumentContent)) {
                log.warn("Content is not belong to QrCodeDocumentContent: {}", documentRequest.contents().getClass());
                return;
            }

            for (String content : qrCodeDocumentContent.getQrCodes()) {
                BitMatrix bitMatrix = qrCodeWriter.encode(
                        content,
                        BarcodeFormat.QR_CODE,
                        qrCodeWidth,
                        qrCodeHeight
                );

                String qrCodeFileName = "temp_qrcode_" + RandomCodeGenerator.generateAlpNumRandomCode(6) + ".png";
                var qrCodePath = Path.of(outputPath, qrCodeFileName);
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodePath);

                var qrCodeImage = new Image(ImageDataFactory.create(qrCodePath.toString()));
                pdfDocument.add(qrCodeImage);
                qrCodePath.toFile().delete();
            }

            onDocumentReadyForDownload.accept(pdfPath.toString());
            log.info("QR code document generation completed for filename: {}", filename);
        } catch (Exception e) {
            log.error("Error generating QR code document: ", e);
            throw new RuntimeException("Failed to generate QR code document", e);
        }
    }
}
