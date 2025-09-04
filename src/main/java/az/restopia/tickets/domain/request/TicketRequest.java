package az.restopia.tickets.domain.request;

import az.restopia.tickets.domain.enums.TicketPriority;
import az.restopia.tickets.domain.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    @NotNull(message = "Status is required")
    private TicketStatus status;
    
    @NotNull(message = "Priority is required")
    private TicketPriority priority;
    
    private String assignedTo;
    
    @NotBlank(message = "Created by is required")
    private String createdBy;
    
    private String category;
    
    private String tags;
}