package az.neotech.neoeats.task.domain.response;

import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import az.neotech.neoeats.task.domain.enums.TaskPriority;
import az.neotech.neoeats.task.domain.enums.TaskStatus;

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