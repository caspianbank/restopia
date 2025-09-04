package az.restopia.business.repository;

import az.restopia.business.domain.entity.TimeOffRequestEntity;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequestEntity, Long> {
    
    Optional<TimeOffRequestEntity> findByIdAndTenantCodeAndDeleteStatus(
        Long id, 
        String tenantCode, 
        DeleteStatus deleteStatus
    );
    
    Long countByTenantCodeAndDeleteStatus(String tenantCode, DeleteStatus deleteStatus);
}