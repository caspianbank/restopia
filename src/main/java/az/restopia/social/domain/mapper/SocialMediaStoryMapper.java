package az.restopia.social.domain.mapper;

import az.restopia.social.domain.entity.SocialMediaStory;
import az.restopia.social.domain.request.SocialMediaStoryRequest;
import az.restopia.social.domain.response.SocialMediaStoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SocialMediaStoryMapper {
    SocialMediaStory toEntity(SocialMediaStoryRequest request);
    SocialMediaStoryResponse toResponse(SocialMediaStory entity);
    void updateEntity(@MappingTarget SocialMediaStory entity, SocialMediaStoryRequest request);
}