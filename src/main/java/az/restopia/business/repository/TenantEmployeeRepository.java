package az.restopia.business.repository;

import az.restopia.business.domain.entity.TenantEmployee;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface TenantEmployeeRepository extends JpaRepository<TenantEmployee, Long> {

    Page<TenantEmployee> findByTenantCodeAndDeleteStatus(
            String tenantCode,
            DeleteStatus deleteStatus,
            Pageable pageable
    );

    Optional<TenantEmployee> findByIdAndTenantCodeAndDeleteStatus(
            Long id,
            String tenantCode,
            DeleteStatus deleteStatus
    );

    @Query("SELECT e FROM TenantEmployee e WHERE e.tenantCode = :tenantCode " +
            "AND e.deleteStatus = :deleteStatus " +
            "AND (LOWER(e.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(e.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<TenantEmployee> searchEmployees(
            @Param("tenantCode") String tenantCode,
            @Param("deleteStatus") DeleteStatus deleteStatus,
            @Param("searchTerm") String searchTerm,
            Pageable pageable
    );

    Page<TenantEmployee> findByTenantCodeAndDeleteStatusAndRole(
            String tenantCode,
            DeleteStatus deleteStatus,
            String role,
            Pageable pageable
    );

    long countByTenantCodeAndDeleteStatus(String tenantCode, DeleteStatus deleteStatus);

    long countByTenantCodeAndDeleteStatusAndIsActive(String tenantCode, DeleteStatus deleteStatus, boolean isActive);
}