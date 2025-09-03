package az.restopia.task.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.task.domain.entity.Task;
import az.restopia.task.domain.enums.TaskPriority;
import az.restopia.task.domain.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByDeleteStatusNot(DeleteStatus deleteStatus, Pageable pageable);

    Optional<Task> findByIdAndDeleteStatusNot(Long id, DeleteStatus deleteStatus);

    Page<Task> findByStatusAndDeleteStatusNot(TaskStatus status, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByPriorityAndDeleteStatusNot(TaskPriority priority, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByAssignedToAndDeleteStatusNot(String assignedTo, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByAssignedByAndDeleteStatusNot(String assignedBy, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCaseAndDeleteStatusNot(String title, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByDescriptionContainingIgnoreCaseAndDeleteStatusNot(String description, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByTagsContainingIgnoreCaseAndDeleteStatusNot(String tags, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByDueDateBetweenAndDeleteStatusNot(LocalDateTime startDate, LocalDateTime endDate, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByDueDateBeforeAndDeleteStatusNot(LocalDateTime date, DeleteStatus deleteStatus, Pageable pageable);

    Page<Task> findByProgressPercentageBetweenAndDeleteStatusNot(Integer minProgress, Integer maxProgress, DeleteStatus deleteStatus, Pageable pageable);

    long countByStatusAndDeleteStatusNot(TaskStatus status, DeleteStatus deleteStatus);

    long countByPriorityAndDeleteStatusNot(TaskPriority priority, DeleteStatus deleteStatus);

    long countByAssignedToAndDeleteStatusNot(String assignedTo, DeleteStatus deleteStatus);

    long countByDeleteStatusNot(DeleteStatus deleteStatus);

    @Query("SELECT t FROM Task t WHERE t.tenantCode = :tenantCode AND t.deleteStatus != :deleteStatus")
    Page<Task> findByTenantCodeAndDeleteStatusNot(@Param("tenantCode") String tenantCode, 
                                                  @Param("deleteStatus") DeleteStatus deleteStatus, 
                                                  Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.tenantCode = :tenantCode AND t.status = :status AND t.deleteStatus != :deleteStatus")
    Page<Task> findByTenantCodeAndStatusAndDeleteStatusNot(@Param("tenantCode") String tenantCode,
                                                          @Param("status") TaskStatus status,
                                                          @Param("deleteStatus") DeleteStatus deleteStatus,
                                                          Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.tenantCode = :tenantCode AND t.assignedTo = :assignedTo AND t.deleteStatus != :deleteStatus")
    Page<Task> findByTenantCodeAndAssignedToAndDeleteStatusNot(@Param("tenantCode") String tenantCode,
                                                              @Param("assignedTo") String assignedTo,
                                                              @Param("deleteStatus") DeleteStatus deleteStatus,
                                                              Pageable pageable);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.tenantCode = :tenantCode AND t.deleteStatus != :deleteStatus")
    long countByTenantCodeAndDeleteStatusNot(@Param("tenantCode") String tenantCode, 
                                           @Param("deleteStatus") DeleteStatus deleteStatus);

    @Query("SELECT t FROM Task t WHERE t.dueDate < :currentDate AND t.status NOT IN :completedStatuses AND t.deleteStatus != :deleteStatus")
    Page<Task> findOverdueTasks(@Param("currentDate") LocalDateTime currentDate,
                               @Param("completedStatuses") List<TaskStatus> completedStatuses,
                               @Param("deleteStatus") DeleteStatus deleteStatus,
                               Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :startDate AND :endDate AND t.deleteStatus != :deleteStatus ORDER BY t.dueDate ASC")
    List<Task> findTasksDueInPeriod(@Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate,
                                   @Param("deleteStatus") DeleteStatus deleteStatus);
}