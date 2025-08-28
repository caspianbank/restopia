package az.neotech.neoeats.task.controller;

import az.neotech.neoeats.task.domain.enums.TaskPriority;
import az.neotech.neoeats.task.domain.enums.TaskStatus;
import az.neotech.neoeats.task.domain.request.TaskRequest;
import az.neotech.neoeats.task.domain.response.TaskResponse;
import az.neotech.neoeats.task.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    
    private final TaskService taskService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks with pagination: {}", pageable);
        Page<TaskResponse> tasks = taskService.getAllTasks(pageable);
        log.info("Retrieved {} tasks", tasks.getTotalElements());
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable @Positive Long id) {
        
        log.debug("Fetching task with ID: {}", id);
        TaskResponse task = taskService.getTaskById(id);
        
        return ResponseEntity.ok(task);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        
        log.info("Creating new task: {}", request.title());
        TaskResponse createdTask = taskService.createTask(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable @Positive Long id,
            @Valid @RequestBody TaskRequest request) {
        
        log.info("Updating task with ID: {}", id);
        TaskResponse updatedTask = taskService.updateTask(id, request);
        log.info("Successfully updated task with ID: {}", id);
        
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive Long id) {
        
        log.warn("Deleting task with ID: {}", id);
        taskService.deleteTask(id);
        log.info("Successfully deleted task with ID: {}", id);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByStatus(
            @PathVariable TaskStatus status,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks with status: {}", status);
        Page<TaskResponse> tasks = taskService.getTasksByStatus(status, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority/{priority}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByPriority(
            @PathVariable TaskPriority priority,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks with priority: {}", priority);
        Page<TaskResponse> tasks = taskService.getTasksByPriority(priority, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assigned-to")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByAssignedTo(
            @RequestParam String assignedTo,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks assigned to: {}", assignedTo);
        Page<TaskResponse> tasks = taskService.getTasksByAssignedTo(assignedTo, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assigned-by")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByAssignedBy(
            @RequestParam String assignedBy,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks assigned by: {}", assignedBy);
        Page<TaskResponse> tasks = taskService.getTasksByAssignedBy(assignedBy, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/title")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> searchTasksByTitle(
            @RequestParam String title,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Searching tasks by title: {}", title);
        Page<TaskResponse> tasks = taskService.searchTasksByTitle(title, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/description")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> searchTasksByDescription(
            @RequestParam String description,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Searching tasks by description: {}", description);
        Page<TaskResponse> tasks = taskService.searchTasksByDescription(description, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/tags")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> searchTasksByTags(
            @RequestParam String tags,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Searching tasks by tags: {}", tags);
        Page<TaskResponse> tasks = taskService.searchTasksByTags(tags, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks between {} and {}", startDate, endDate);
        Page<TaskResponse> tasks = taskService.getTasksByDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/overdue")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getOverdueTasks(
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching overdue tasks");
        Page<TaskResponse> tasks = taskService.getOverdueTasks(pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/progress-range")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByProgressRange(
            @RequestParam @Min(0) @Max(100) Integer minProgress,
            @RequestParam @Min(0) @Max(100) Integer maxProgress,
            @PageableDefault(size = 20, sort = "progressPercentage") Pageable pageable) {
        
        log.debug("Fetching tasks with progress between {}% and {}%", minProgress, maxProgress);
        Page<TaskResponse> tasks = taskService.getTasksByProgressRange(minProgress, maxProgress, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}/complete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<TaskResponse> completeTask(
            @PathVariable @Positive Long id) {
        
        log.info("Completing task with ID: {}", id);
        TaskResponse completedTask = taskService.completeTask(id);
        
        return ResponseEntity.ok(completedTask);
    }

    @PutMapping("/{id}/start")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<TaskResponse> startTask(
            @PathVariable @Positive Long id) {
        
        log.info("Starting task with ID: {}", id);
        TaskResponse startedTask = taskService.startTask(id);
        
        return ResponseEntity.ok(startedTask);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<TaskResponse> cancelTask(
            @PathVariable @Positive Long id) {
        
        log.info("Cancelling task with ID: {}", id);
        TaskResponse cancelledTask = taskService.cancelTask(id);
        
        return ResponseEntity.ok(cancelledTask);
    }

    @PutMapping("/{id}/hold")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<TaskResponse> holdTask(
            @PathVariable @Positive Long id) {
        
        log.info("Putting task on hold with ID: {}", id);
        TaskResponse heldTask = taskService.holdTask(id);
        
        return ResponseEntity.ok(heldTask);
    }

    @PutMapping("/{id}/progress")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<TaskResponse> updateTaskProgress(
            @PathVariable @Positive Long id,
            @RequestParam @Min(0) @Max(100) Integer progressPercentage) {
        
        log.info("Updating progress for task with ID: {} to {}%", id, progressPercentage);
        TaskResponse updatedTask = taskService.updateTaskProgress(id, progressPercentage);
        
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable @Positive Long id,
            @RequestParam String assignedTo) {
        
        log.info("Assigning task with ID: {} to {}", id, assignedTo);
        TaskResponse assignedTask = taskService.assignTask(id, assignedTo);
        
        return ResponseEntity.ok(assignedTask);
    }

    @GetMapping("/tenant/{tenantCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByTenant(
            @PathVariable String tenantCode,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks for tenant: {}", tenantCode);
        Page<TaskResponse> tasks = taskService.getTasksByTenant(tenantCode, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tenant/{tenantCode}/status/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByTenantAndStatus(
            @PathVariable String tenantCode,
            @PathVariable TaskStatus status,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks for tenant: {} with status: {}", tenantCode, status);
        Page<TaskResponse> tasks = taskService.getTasksByTenantAndStatus(tenantCode, status, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tenant/{tenantCode}/assigned-to")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Page<TaskResponse>> getTasksByTenantAndAssignedTo(
            @PathVariable String tenantCode,
            @RequestParam String assignedTo,
            @PageableDefault(size = 20, sort = "dueDate") Pageable pageable) {
        
        log.debug("Fetching tasks for tenant: {} assigned to: {}", tenantCode, assignedTo);
        Page<TaskResponse> tasks = taskService.getTasksByTenantAndAssignedTo(tenantCode, assignedTo, pageable);
        
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/stats/count/status/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Long> countTasksByStatus(
            @PathVariable TaskStatus status) {
        
        long count = taskService.countTasksByStatus(status);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/count/priority/{priority}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Long> countTasksByPriority(
            @PathVariable TaskPriority priority) {
        
        long count = taskService.countTasksByPriority(priority);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/count/assigned-to")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Long> countTasksByAssignedTo(
            @RequestParam String assignedTo) {
        
        long count = taskService.countTasksByAssignedTo(assignedTo);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/count/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Long> countAllTasks() {
        
        long count = taskService.countAllTasks();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/count/tenant/{tenantCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Long> countTasksByTenant(@PathVariable String tenantCode) {
        long count = taskService.countTasksByTenant(tenantCode);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/due-period")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<TaskResponse>> getTasksDueInPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        log.debug("Fetching tasks due between {} and {}", startDate, endDate);
        List<TaskResponse> tasks = taskService.getTasksDueInPeriod(startDate, endDate);
        
        return ResponseEntity.ok(tasks);
    }
}
