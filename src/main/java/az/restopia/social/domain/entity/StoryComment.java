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
@Table(name = "story_comments")
public class StoryComment extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "story_id", nullable = false)
    private Long storyId;

    @Column(name = "user_id")
    private Long userId; // can be nullable for unknown users

    @Column(name = "comment_text", nullable = false, length = 1000)
    private String commentText;
}