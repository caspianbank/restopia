package az.restopia.alert.repository;

import az.restopia.alert.domain.entity.Alert;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    Page<Alert> findByTenantCodeAndDeleteStatus(String tenantCode, DeleteStatus deleteStatus, Pageable pageable);

    Optional<Alert> findByIdAndTenantCodeAndDeleteStatus(Long id, String tenantCode, DeleteStatus deleteStatus);

    @Query("SELECT a FROM Alert a WHERE a.tenantCode = :tenantCode " +
           "AND a.deleteStatus = 'NOT_DELETED' " +
           "AND (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(a.description) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(a.createdBy) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(a.assignedTo) LIKE LOWER(CONCAT('%', :q, '%')))")
    Page<Alert> searchAlerts(@Param("tenantCode") String tenantCode,
                            @Param("q") String q,
                            Pageable pageable);
}