package az.restopia.task.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.task.domain.entity.Task;
import az.restopia.task.domain.enums.TaskPriority;
import az.restopia.task.domain.enums.TaskStatus;
import az.restopia.task.domain.mapper.TaskMapper;
import az.restopia.task.domain.request.TaskRequest;
import az.restopia.task.domain.response.TaskResponse;
import az.restopia.task.repository.TaskRepository;
import az.restopia.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Task getTaskEntity(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Task not found"));
    }

    @Override
    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        log.info("Fetching all non-deleted tasks with pagination: {}", pageable);
        Page<Task> tasks = taskRepository.findByDeleteStatusNot(DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks", tasks.getTotalElements());
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        log.info("Fetching task with id={}", id);
        Task task = taskRepository.findByIdAndDeleteStatusNot(id, DeleteStatus.DELETED)
                .orElseThrow(() -> {
                    log.warn("Task with id={} not found", id);
                    return new RecordNotFoundException("Task not found with id: " + id);
                });
        log.debug("Found task: {}", task.getTitle());
        return taskMapper.toResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        log.info("Creating task with tenantCode={} and title={}", request.tenantCode(), request.title());

        //todo: validateTaskRequest(request);

        Task task = taskMapper.toEntity(request);
        task.setDeleteStatus(DeleteStatus.ACTIVE);

        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.PENDING);
        }

        if (task.getProgressPercentage() == null) {
            task.setProgressPercentage(0);
        }

        Task saved = taskRepository.save(task);
        log.debug("Created task with id={}", saved.getId());
        return taskMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        log.info("Updating task with id={}", id);

        //todo: validateTaskRequest(request);

        Task task = taskRepository.findByIdAndDeleteStatusNot(id, DeleteStatus.DELETED)
                .orElseThrow(() -> {
                    log.warn("Task with id={} not found for update", id);
                    return new RecordNotFoundException("Task not found with id: " + id);
                });

        taskMapper.update(task, request);
        Task updated = taskRepository.save(task);
        log.debug("Updated task with id={}", updated.getId());
        return taskMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        log.info("Soft deleting task with id={}", id);

        Task task = taskRepository.findByIdAndDeleteStatusNot(id, DeleteStatus.DELETED)
                .orElseThrow(() -> {
                    log.warn("Task with id={} not found for deletion", id);
                    return new RecordNotFoundException("Task not found with id: " + id);
                });

        task.setDeleteStatus(DeleteStatus.DELETED);
        taskRepository.save(task);
        log.debug("Soft deleted task with id={}", id);
    }

    @Override
    public Page<TaskResponse> getTasksByStatus(TaskStatus status, Pageable pageable) {
        log.info("Fetching tasks with status: {}", status);
        Page<Task> tasks = taskRepository.findByStatusAndDeleteStatusNot(status, DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks with status: {}", tasks.getTotalElements(), status);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getTasksByPriority(TaskPriority priority, Pageable pageable) {
        log.info("Fetching tasks with priority: {}", priority);
        Page<Task> tasks = taskRepository.findByPriorityAndDeleteStatusNot(priority, DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks with priority: {}", tasks.getTotalElements(), priority);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getTasksByAssignedTo(String assignedTo, Pageable pageable) {
        if (!StringUtils.hasText(assignedTo)) {
            throw new IllegalArgumentException("Assigned to cannot be null or empty");
        }

        log.info("Fetching tasks assigned to: {}", assignedTo);
        Page<Task> tasks = taskRepository.findByAssignedToAndDeleteStatusNot(assignedTo.trim(), DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks assigned to: {}", tasks.getTotalElements(), assignedTo);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getTasksByAssignedBy(String assignedBy, Pageable pageable) {
        if (!StringUtils.hasText(assignedBy)) {
            throw new IllegalArgumentException("Assigned by cannot be null or empty");
        }

        log.info("Fetching tasks assigned by: {}", assignedBy);
        Page<Task> tasks = taskRepository.findByAssignedByAndDeleteStatusNot(assignedBy.trim(), DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks assigned by: {}", tasks.getTotalElements(), assignedBy);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> searchTasksByTitle(String title, Pageable pageable) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Search title cannot be null or empty");
        }

        log.info("Searching tasks by title: {}", title);
        Page<Task> tasks = taskRepository.findByTitleContainingIgnoreCaseAndDeleteStatusNot(title.trim(), DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks matching title: {}", tasks.getTotalElements(), title);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> searchTasksByDescription(String description, Pageable pageable) {
        if (!StringUtils.hasText(description)) {
            throw new IllegalArgumentException("Search description cannot be null or empty");
        }

        log.info("Searching tasks by description: {}", description);
        Page<Task> tasks = taskRepository.findByDescriptionContainingIgnoreCaseAndDeleteStatusNot(description.trim(), DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks matching description: {}", tasks.getTotalElements(), description);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> searchTasksByTags(String tags, Pageable pageable) {
        if (!StringUtils.hasText(tags)) {
            throw new IllegalArgumentException("Search tags cannot be null or empty");
        }

        log.info("Searching tasks by tags: {}", tags);
        Page<Task> tasks = taskRepository.findByTagsContainingIgnoreCaseAndDeleteStatusNot(tags.trim(), DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks matching tags: {}", tasks.getTotalElements(), tags);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getTasksByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        log.info("Fetching tasks between {} and {}", startDate, endDate);
        Page<Task> tasks = taskRepository.findByDueDateBetweenAndDeleteStatusNot(startDate, endDate, DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks in date range", tasks.getTotalElements());
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getOverdueTasks(Pageable pageable) {
        log.info("Fetching overdue tasks");
        LocalDateTime currentDate = LocalDateTime.now();
        List<TaskStatus> completedStatuses = Arrays.asList(TaskStatus.COMPLETED, TaskStatus.CANCELLED);

        Page<Task> tasks = taskRepository.findOverdueTasks(currentDate, completedStatuses, DeleteStatus.DELETED, pageable);
        log.debug("Found {} overdue tasks", tasks.getTotalElements());
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponse> getTasksByProgressRange(Integer minProgress, Integer maxProgress, Pageable pageable) {
        if (minProgress == null || maxProgress == null) {
            throw new IllegalArgumentException("Progress range cannot be null");
        }

        if (minProgress < 0 || maxProgress > 100 || minProgress > maxProgress) {
            throw new IllegalArgumentException("Invalid progress range");
        }

        log.info("Fetching tasks with progress between {}% and {}%", minProgress, maxProgress);
        Page<Task> tasks = taskRepository.findByProgressPercentageBetweenAndDeleteStatusNot(minProgress, maxProgress, DeleteStatus.DELETED, pageable);
        log.debug("Found {} tasks in progress range", tasks.getTotalElements());
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    @Transactional
    public TaskResponse completeTask(Long id) {
        log.info("Completing task with id={}", id);

        Task task = getTaskEntity(id);

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Task is already completed");
        }

        if (task.getStatus() == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot complete a cancelled task");
        }

        task.setStatus(TaskStatus.COMPLETED);
        task.setProgressPercentage(100);
        task.setCompletedDate(LocalDateTime.now());

        Task updated = taskRepository.save(task);
        log.debug("Completed task with id={}", id);
        return taskMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public TaskResponse startTask(Long id) {
        log.info("Starting task with id={}", id);

        Task task = getTaskEntity(id);

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot start a completed task");
        }

        if (task.getStatus() == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot start a cancelled task");
        }

        task.setStatus(TaskStatus.IN_PROGRESS);

        if (task.getProgressPercentage() == 0) {
            task.setProgressPercentage(1);
        }

        Task updated = taskRepository.save(task);
        log.debug("Started task with id={}", id);
        return taskMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public TaskResponse cancelTask(Long id) {
        log.info("Cancelling task with id={}", id);

        Task task = getTaskEntity(id);

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a completed task");
        }

        task.setStatus(TaskStatus.CANCELLED);

        Task updated = taskRepository.save(task);
        log.debug("Cancelled task with id={}", id);
        return taskMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public TaskResponse holdTask(Long id) {
        log.info("Putting task on hold with id={}", id);

        Task task = getTaskEntity(id);

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot hold a completed task");
        }

        if (task.getStatus() == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot hold a cancelled task");
        }

        task.setStatus(TaskStatus.ON_HOLD);

        Task updated = taskRepository.save(task);
        log.debug("Put task on hold with id={}", id);
        return taskMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskProgress(Long id, Integer progressPercentage) {
        if (progressPercentage == null || progressPercentage < 0 || progressPercentage > 100) {
            throw new IllegalArgumentException("Progress percentage must be between 0 and 100");
        }

        log.info("Updating task progress with id={} to {}%", id, progressPercentage);

        Task task = getTaskEntity(id);

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot update progress of a completed task");
        }

        if (task.getStatus() == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot update progress of a cancelled task");
        }

        task.setProgressPercentage(progressPercentage);

        if (progressPercentage == 100 && task.getStatus() != TaskStatus.COMPLETED) {
            task.setStatus(TaskStatus.COMPLETED);
            task.setCompletedDate(LocalDateTime.now());
        } else if (progressPercentage > 0 && task.getStatus() == TaskStatus.PENDING) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }

        Task updated = taskRepository.save(task);
        log.debug("Updated task progress with id={}", id);
        return taskMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public TaskResponse assignTask(Long id, String assignedTo) {
        if (!StringUtils.hasText(assignedTo)) {
            throw new IllegalArgumentException("Assigned to cannot be null or empty");
        }

        log.info("Assigning task with id={} to {}", id, assignedTo);

        Task task = getTaskEntity(id);
        task.setAssignedTo(assignedTo.trim());

        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public Page<TaskResponse> getTasksByTenant(String tenantCode, Pageable pageable) {
        return null;
    }

    @Override
    public Page<TaskResponse> getTasksByTenantAndStatus(String tenantCode, TaskStatus status, Pageable pageable) {
        return null;
    }

    @Override
    public Page<TaskResponse> getTasksByTenantAndAssignedTo(String tenantCode, String assignedTo, Pageable pageable) {
        return null;
    }

    @Override
    public long countTasksByStatus(TaskStatus status) {
        return 0;
    }

    @Override
    public long countTasksByPriority(TaskPriority priority) {
        return 0;
    }

    @Override
    public long countTasksByAssignedTo(String assignedTo) {
        return 0;
    }

    @Override
    public long countAllTasks() {
        return 0;
    }

    @Override
    public long countTasksByTenant(String tenantCode) {
        return 0;
    }

    @Override
    public List<TaskResponse> getTasksDueInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return List.of();
    }
}