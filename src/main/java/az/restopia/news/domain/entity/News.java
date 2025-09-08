package az.restopia.news.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News extends DateAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 500)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @Column(length = 100)
    private String category;
    
    @Column(nullable = false)
    private Boolean published = false;
    
    @Column(nullable = false, length = 10)
    private String tenantCode;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}