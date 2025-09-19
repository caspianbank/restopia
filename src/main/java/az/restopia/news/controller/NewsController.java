package az.restopia.news.controller;

import az.restopia.news.domain.response.NewsResponse;
import az.restopia.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Validated
@Slf4j
public class NewsController {
    
    private final NewsService newsService;
    
    @GetMapping
    public ResponseEntity<List<NewsResponse>> getAllNews() {
        log.info("Received request to get all news");
        
        List<NewsResponse> newsResponseList = newsService.getAllNews();
        
        log.info("Returning {} news items", newsResponseList.size());
        return ResponseEntity.ok(newsResponseList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNewsById(@PathVariable Long id) {
        log.info("Received request to get news by id: {}", id);
        
        NewsResponse newsResponse = newsService.getNewsById(id);
        
        log.info("Returning news with id: {}", id);
        return ResponseEntity.ok(newsResponse);
    }
}