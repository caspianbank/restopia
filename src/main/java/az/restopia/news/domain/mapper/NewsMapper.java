package az.restopia.news.domain.mapper;

import az.restopia.news.domain.entity.News;
import az.restopia.news.domain.response.NewsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    
    NewsResponse toResponse(News news);
    
    List<NewsResponse> toResponseList(List<News> newsList);
}