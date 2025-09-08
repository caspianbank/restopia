package az.restopia.news.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.news.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    
    List<News> findAllByDeleteStatusAndPublishedOrderByCreatedAtDesc(DeleteStatus deleteStatus, Boolean published);
    
    Optional<News> findByIdAndDeleteStatus(Long id, DeleteStatus deleteStatus);
}