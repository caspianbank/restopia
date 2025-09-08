package az.restopia.news.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.news.domain.entity.News;
import az.restopia.news.domain.mapper.NewsMapper;
import az.restopia.news.domain.response.NewsResponse;
import az.restopia.news.repository.NewsRepository;
import az.restopia.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {
    
    private final NewsRepository newsRepository;
    
    @Override
    public List<NewsResponse> getAllNews() {
        log.info("Getting all published news");
        
        List<News> newsList = newsRepository.findAllByDeleteStatusAndPublishedOrderByCreatedAtDesc(
                DeleteStatus.ACTIVE, true);
        
        log.info("Found {} published news", newsList.size());
        return NewsMapper.INSTANCE.toResponseList(newsList);
    }
    
    @Override
    public NewsResponse getNewsById(Long id) {
        log.info("Getting news by id: {}", id);
        
        News news = newsRepository.findByIdAndDeleteStatus(id, DeleteStatus.ACTIVE)
                .orElseThrow(() -> {
                    log.error("News not found with id: {}", id);
                    return new RecordNotFoundException("News not found with id: " + id);
                });
        
        if (!news.getPublished()) {
            log.error("News with id: {} is not published", id);
            throw new RecordNotFoundException("News not found with id: " + id);
        }
        
        log.info("News found with id: {}", id);
        return NewsMapper.INSTANCE.toResponse(news);
    }
}