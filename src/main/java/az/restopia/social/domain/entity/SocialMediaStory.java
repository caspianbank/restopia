package az.restopia.social.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.social.domain.enums.MediaType;
import az.restopia.social.domain.enums.StoryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "social_media_stories")
public class SocialMediaStory extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "caption", length = 1000)
    private String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false, length = 20)
    private MediaType mediaType;

    @Column(name = "media_url", nullable = false, length = 500)
    private String mediaUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StoryStatus status;

    // Denormalized counters
    @Column(name = "likes_count", nullable = false)
    private Long likesCount = 0L;

    @Column(name = "comments_count", nullable = false)
    private Long commentsCount = 0L;

    @Column(name = "shares_count", nullable = false)
    private Long sharesCount = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_status", nullable = false, length = 20)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}