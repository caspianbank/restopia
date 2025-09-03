package az.restopia.menu.domain.request;

public record QrCodeRequest(
        String tenantCode,
        String text,
        String logoUrl
) {

}
