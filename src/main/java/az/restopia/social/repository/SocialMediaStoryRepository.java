package az.restopia.social.repository;

import az.restopia.social.domain.entity.SocialMediaStory;
import az.restopia.social.domain.enums.StoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaStoryRepository extends JpaRepository<SocialMediaStory, Long> {
    List<SocialMediaStory> findAllByTenantCodeAndStatus(String tenantCode, StoryStatus status);
}