package az.restopia.task.domain.response;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.task.domain.enums.TaskPriority;
import az.restopia.task.domain.enums.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(
        String tenantCode,
        String title,
        String description,
        TaskPriority priority,
        String assignedTo,
        String assignedBy,
        LocalDateTime dueDate,
        LocalDateTime completedDate,
        TaskStatus status,
        String tags,
        Integer estimatedHours,
        Integer actualHours,
        Integer progressPercentage,
        DeleteStatus deleteStatus,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}