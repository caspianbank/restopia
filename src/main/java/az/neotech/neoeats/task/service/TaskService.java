package az.neotech.neoeats.task.service;

import az.neotech.neoeats.task.domain.enums.TaskPriority;
import az.neotech.neoeats.task.domain.enums.TaskStatus;
import az.neotech.neoeats.task.domain.request.TaskRequest;
import az.neotech.neoeats.task.domain.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    TaskResponse updateTask(Long id, TaskRequest request);

    Page<TaskResponse> getAllTasks(Pageable pageable);

    TaskResponse getTaskById(Long id);

    void deleteTask(Long id);

    Page<TaskResponse> getTasksByStatus(TaskStatus status, Pageable pageable);

    Page<TaskResponse> getTasksByPriority(TaskPriority priority, Pageable pageable);

    Page<TaskResponse> getTasksByAssignedTo(String assignedTo, Pageable pageable);

    Page<TaskResponse> getTasksByAssignedBy(String assignedBy, Pageable pageable);

    Page<TaskResponse> searchTasksByTitle(String title, Pageable pageable);

    Page<TaskResponse> searchTasksByDescription(String description, Pageable pageable);

    Page<TaskResponse> searchTasksByTags(String tags, Pageable pageable);

    Page<TaskResponse> getTasksByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<TaskResponse> getOverdueTasks(Pageable pageable);

    Page<TaskResponse> getTasksByProgressRange(Integer minProgress, Integer maxProgress, Pageable pageable);

    TaskResponse completeTask(Long id);

    TaskResponse startTask(Long id);

    TaskResponse cancelTask(Long id);

    TaskResponse holdTask(Long id);

    TaskResponse updateTaskProgress(Long id, Integer progressPercentage);

    TaskResponse assignTask(Long id, String assignedTo);

    boolean existsById(Long id);

    Page<TaskResponse> getTasksByTenant(String tenantCode, Pageable pageable);

    Page<TaskResponse> getTasksByTenantAndStatus(String tenantCode, TaskStatus status, Pageable pageable);

    Page<TaskResponse> getTasksByTenantAndAssignedTo(String tenantCode, String assignedTo, Pageable pageable);

    long countTasksByStatus(TaskStatus status);

    long countTasksByPriority(TaskPriority priority);

    long countTasksByAssignedTo(String assignedTo);

    long countAllTasks();

    long countTasksByTenant(String tenantCode);

    List<TaskResponse> getTasksDueInPeriod(LocalDateTime startDate, LocalDateTime endDate);
}