package az.neotech.neoeats.menu.domain.request;

public record QrCodeRequest(
        String tenantCode,
        String text,
        String logoUrl
) {

}
