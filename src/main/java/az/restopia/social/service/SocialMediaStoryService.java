package az.restopia.social.service;

import az.restopia.social.domain.request.SocialMediaStoryRequest;
import az.restopia.social.domain.response.SocialMediaStoryResponse;

import java.util.List;

public interface SocialMediaStoryService {
    List<SocialMediaStoryResponse> getStories(String tenantCode);

    SocialMediaStoryResponse getStoryById(Long id);

    SocialMediaStoryResponse createStory(SocialMediaStoryRequest request);

    SocialMediaStoryResponse publishStory(Long id);

    SocialMediaStoryResponse updateStory(Long id, SocialMediaStoryRequest request);

    void deleteStory(Long id);

    void likeStory(Long id, Long userId);

    void commentStory(Long id, Long userId, String comment);

    String shareStory(Long id, Long userId);
}