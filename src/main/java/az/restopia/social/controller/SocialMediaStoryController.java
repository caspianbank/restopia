package az.restopia.social.controller;

import az.restopia.social.domain.request.SocialMediaStoryRequest;
import az.restopia.social.domain.response.SocialMediaStoryResponse;
import az.restopia.social.service.SocialMediaStoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class SocialMediaStoryController {

    private final SocialMediaStoryService service;

    @GetMapping
    public List<SocialMediaStoryResponse> getStories(@RequestParam String tenantCode) {
        return service.getStories(tenantCode);
    }

    @GetMapping("/{id}")
    public SocialMediaStoryResponse getStory(@PathVariable Long id) {
        return service.getStoryById(id);
    }

    @PostMapping
    public SocialMediaStoryResponse createStory(@Valid @RequestBody SocialMediaStoryRequest request) {
        return service.createStory(request);
    }

    @PostMapping("/{id}/publish")
    public SocialMediaStoryResponse publishStory(@PathVariable Long id) {
        return service.publishStory(id);
    }

    @PutMapping("/{id}")
    public SocialMediaStoryResponse updateStory(@PathVariable Long id,
                                                @Valid @RequestBody SocialMediaStoryRequest request) {
        return service.updateStory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteStory(@PathVariable Long id) {
        service.deleteStory(id);
    }

    @PostMapping("/{id}/like")
    public void likeStory(@PathVariable Long id, @RequestParam Long userId) {
        service.likeStory(id, userId);
    }

    @PostMapping("/{id}/comment")
    public void commentStory(@PathVariable Long id,
                             @RequestParam Long userId,
                             @RequestParam String comment) {
        service.commentStory(id, userId, comment);
    }

    @PostMapping("/{id}/share")
    public String shareStory(@PathVariable Long id, @RequestParam Long userId) {
        return service.shareStory(id, userId);
    }
}