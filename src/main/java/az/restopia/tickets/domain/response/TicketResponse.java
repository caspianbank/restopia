package az.restopia.tickets.domain.response;

import az.restopia.tickets.domain.enums.TicketPriority;
import az.restopia.tickets.domain.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketResponse {
    
    private Long id;
    private String tenantCode;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private String assignedTo;
    private String createdBy;
    private String category;
    private String tags;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}