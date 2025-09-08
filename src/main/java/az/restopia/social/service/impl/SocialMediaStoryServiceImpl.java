package az.restopia.social.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.social.domain.entity.SocialMediaStory;
import az.restopia.social.domain.enums.StoryStatus;
import az.restopia.social.domain.mapper.SocialMediaStoryMapper;
import az.restopia.social.domain.request.SocialMediaStoryRequest;
import az.restopia.social.domain.response.SocialMediaStoryResponse;
import az.restopia.social.repository.SocialMediaStoryRepository;
import az.restopia.social.service.SocialMediaStoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialMediaStoryServiceImpl implements SocialMediaStoryService {

    private final SocialMediaStoryRepository repository;
    private final SocialMediaStoryMapper mapper;

    @Override
    public List<SocialMediaStoryResponse> getStories(String tenantCode) {
        return repository.findAllByTenantCodeAndStatus(tenantCode, StoryStatus.PUBLISHED)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public SocialMediaStoryResponse getStoryById(Long id) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        return mapper.toResponse(story);
    }

    @Override
    public SocialMediaStoryResponse createStory(SocialMediaStoryRequest request) {
        SocialMediaStory story = mapper.toEntity(request);
        story.setStatus(StoryStatus.DRAFT);
        repository.save(story);
        return mapper.toResponse(story);
    }

    @Override
    public SocialMediaStoryResponse publishStory(Long id) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        story.setStatus(StoryStatus.PUBLISHED);
        story.setStartTime(LocalDateTime.now());
        story.setEndTime(LocalDateTime.now().plusHours(24));
        repository.save(story);
        return mapper.toResponse(story);
    }

    @Override
    public SocialMediaStoryResponse updateStory(Long id, SocialMediaStoryRequest request) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        mapper.updateEntity(story, request);
        repository.save(story);
        return mapper.toResponse(story);
    }

    @Override
    public void deleteStory(Long id) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        repository.delete(story);
    }

    // todo: consider multiple people liked the story at the same time, in order to not lose each like
    //  create a better system. it is actual issue for comment and shares too.
    @Override
    public void likeStory(Long id, Long userId) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        story.setLikesCount(story.getLikesCount() + 1);
        repository.save(story);
    }

    @Override
    public void commentStory(Long id, Long userId, String comment) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        story.setCommentsCount(story.getCommentsCount() + 1);
        repository.save(story);
    }

    @Override
    public String shareStory(Long id, Long userId) {
        SocialMediaStory story = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Story not found"));
        story.setSharesCount(story.getSharesCount() + 1);
        repository.save(story);

        String token = UUID.randomUUID().toString();
        // persist shareToken in StoryShare entity (omitted for brevity)
        return "/api/v1/stories/share/" + token;
    }
}