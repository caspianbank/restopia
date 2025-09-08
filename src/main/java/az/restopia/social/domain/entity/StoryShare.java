package az.restopia.social.domain.entity;

import az.neotech.commons.audit.DateAudit;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "story_shares")
public class StoryShare extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "story_id", nullable = false)
    private Long storyId;

    @Column(name = "user_id")
    private Long userId; // can be nullable for unknown users

    // Unique token so shared link can be resolved instantly
    @Column(name = "share_token", nullable = false, unique = true, length = 100)
    private String shareToken;
}