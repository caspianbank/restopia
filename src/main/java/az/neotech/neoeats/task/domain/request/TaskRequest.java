package az.neotech.neoeats.task.domain.request;

import az.neotech.neoeats.task.domain.enums.TaskPriority;
import az.neotech.neoeats.task.domain.enums.TaskStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRequest(
    @NotBlank(message = "Tenant code is required")
    String tenantCode,
    
    @NotBlank(message = "Title is required")
    String title,
    
    String description,
    
    @NotNull(message = "Priority is required")
    TaskPriority priority,
    
    String assignedTo,
    
    String assignedBy,
    
    LocalDateTime dueDate,
    
    TaskStatus status,
    
    String tags,
    
    @Min(value = 1, message = "Estimated hours must be at least 1")
    @Max(value = 1000, message = "Estimated hours cannot exceed 1000")
    Integer estimatedHours,
    
    @Min(value = 0, message = "Actual hours cannot be negative")
    @Max(value = 1000, message = "Actual hours cannot exceed 1000")
    Integer actualHours,
    
    @Min(value = 0, message = "Progress percentage must be between 0 and 100")
    @Max(value = 100, message = "Progress percentage must be between 0 and 100")
    Integer progressPercentage
) {}