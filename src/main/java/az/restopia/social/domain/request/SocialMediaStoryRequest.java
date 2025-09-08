package az.restopia.social.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialMediaStoryRequest {

    @NotBlank(message = "Tenant code is required")
    private String tenantCode;

    @NotBlank(message = "Title is required")
    private String title;

    private String caption;

    @NotNull(message = "Media type is required")
    private String mediaType;

    @NotBlank(message = "Media URL is required")
    private String mediaUrl;

    private Integer durationSeconds;
}