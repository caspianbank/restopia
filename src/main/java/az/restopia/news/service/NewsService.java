package az.restopia.news.service;

import az.restopia.news.domain.response.NewsResponse;

import java.util.List;

public interface NewsService {
    
    List<NewsResponse> getAllNews();
    
    NewsResponse getNewsById(Long id);
}