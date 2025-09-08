package az.restopia.social.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialMediaStoryResponse {
    private Long id;
    private String tenantCode;
    private String title;
    private String caption;
    private String mediaType;
    private String mediaUrl;
    private Integer durationSeconds;
    private String status;
    private Long likesCount;
    private Long commentsCount;
    private Long sharesCount;
}