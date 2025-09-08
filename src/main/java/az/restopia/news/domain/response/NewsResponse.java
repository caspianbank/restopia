package az.restopia.news.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewsResponse {
    
    private Long id;
    private String title;
    private String content;
    private String category;
    private Boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}