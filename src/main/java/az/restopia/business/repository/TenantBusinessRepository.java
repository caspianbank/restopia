package az.restopia.business.repository;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantBusinessRepository extends JpaRepository<TenantBusiness, Long> {
    Optional<TenantBusiness> findAllByTenantCode(String tenantCode);

    Optional<TenantBusiness> findByIdAndTenantCodeAndDeleteStatus(
            Long id,
            String tenantCode,
            DeleteStatus deleteStatus
    );
}
